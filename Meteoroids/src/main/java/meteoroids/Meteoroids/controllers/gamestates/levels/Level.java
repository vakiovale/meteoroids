package meteoroids.Meteoroids.controllers.gamestates.levels;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStateGameOver;
import meteoroids.Meteoroids.controllers.gamestates.GameStateMachine;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.utilities.GameTimer;
import meteoroids.Meteoroids.utilities.RandomGenerator;
import meteoroids.Meteoroids.utilities.Text;

/**
 * Abstract class for different levels.
 * 
 * @author vpyyhtia
 *
 */
public abstract class Level extends GameStateMachine {

    protected TextHandler textHandler;
    protected boolean levelFinished;
    protected int finalDestroyCounter;
    protected GameStatePlay play;
    protected GameObjectController objectController;
    
    protected Planet[] planets;
    protected Asteroid[] asteroids;
    
    private float infoTextHeight;
    private float helpTextHeight;
    
    private List<Text> helpTexts;
    
    protected int nextLevelCountDown;
    
    protected int levelFinishedTimer;
    
    public Level(GameStateController controller, GameStatePlay play) {
        super(controller);
        this.play = play;
        finalDestroyCounter = 10;  
        levelFinished = false;
        this.objectController = play.getObjectController();
        this.textHandler = new TextHandler();
        this.infoTextHeight = -120.0f;
        this.helpTextHeight = -250.0f;
        this.nextLevelCountDown = 10000;
        this.helpTexts = new ArrayList<>();
        this.levelFinishedTimer = 30000;
        
        initPlanets();
        initAsteroids();
        initTexts();
    }
    
    protected abstract void initTexts();
    
    protected abstract void initAsteroids();

    protected abstract void initPlanets();
    
    protected abstract boolean checkLevelFinished(float deltaTime);
        
    protected void checkGameOver() {
        if(planets != null) {
            for(int i = 0; i < planets.length; i++) {
                if(planets[i] != null && planets[i].isDead()) {               
                    gameOver(planets[i]);
                    break;
                }
            }
        }
    }
    
    protected void gameOver(GameObject kill) {
        exit();
        if(kill != null) {
            objectController.killGameObject(kill);
        }
    }
    
    @Override
    public void update(float deltaTime) {              
        // Draw texts
        textHandler.draw();
        
        // Decrease level finished timer
        levelFinishedTimer -= deltaTime;
        
        // Check if game is over
        levelFinished = checkLevelFinished(deltaTime);
        if(!levelFinished) {
            checkGameOver();
        }
        if(levelFinished) {
            removeHelpTexts();
            nextLevelCountDown -= deltaTime;
            if(nextLevelCountDown <= 0) {
                play.nextLevel();
            }
        }
    }
    
    private void removeHelpTexts() {
        for(Text text : helpTexts) {
            textHandler.removeText(text);
        }        
    }
    
    protected void levelDone() {
        if(planets != null) {
            for(Planet p : planets) {
                p.revive();
                p.invincible();
            }
        }
        Text textTop = new Text("GOOD JOB!", Game.WIDTH/2-140.0f, Game.HEIGHT/2-250.0f);
        Text text = new Text("LEVEL FINISHED!", Game.WIDTH/2-200.0f, Game.HEIGHT/2-100.0f);
        text.setSize(2);
        textTop.setSize(2);
        textHandler.addText(textTop);
        textHandler.addText(text);
    }

    protected void killAsteroids(float deltaTime) {
        finalDestroyCounter -= deltaTime;
        if(finalDestroyCounter <= 0) {
            play.getObjectController().killRandomAsteroid();
            finalDestroyCounter = 20;
        }
    }
        
    protected void initHugeAsteroid() {
        objectController.createAsteroid(0.0f, Game.HEIGHT*2.0f, 10000.0f, 1000.0f);        
    }

    protected Planet initPlanet() {
        float x;
        float y;
        do {
            x = RandomGenerator.random()*Game.WIDTH;
            y = RandomGenerator.random()*Game.HEIGHT;
        } while(spawningFails(x, y));
        
        float radius = RandomGenerator.random()*100.0f + 10.0f;
        float mass = (RandomGenerator.random()*500000.0f+25000.0f)*(radius/10.0f);
        
        return objectController.getPlanet(x, y, radius, mass);
    }
    
    protected void addHelpText(String helpText) {
        Text text = new Text(helpText, Game.WIDTH/2.0f, Game.HEIGHT/3+helpTextHeight);
        helpTextHeight += 50.0f;
        text.setSize(0);
        textHandler.addText(text);
        helpTexts.add(text);
    }
    
    protected void addLevelText(String levelTextString, String levelSubTextString) {
        Text levelText = new Text(levelTextString, Game.WIDTH/8-100.0f, Game.HEIGHT/3-300.0f);
        Text lineText = new Text("---------", Game.WIDTH/8-100.0f, Game.HEIGHT/3-260.0f);
        levelText.setSize(2);
        Text levelSubText = new Text(levelSubTextString, Game.WIDTH/8-100.0f, Game.HEIGHT/3-220.0f);      
        levelSubText.setSize(0);  
        textHandler.addText(levelText);
        textHandler.addText(lineText);
        textHandler.addText(levelSubText);
        
    }
    
    protected void addLevelInfoText(String levelInfoTextString) {
        Text levelInfoText = new Text("- " + levelInfoTextString, Game.WIDTH/8-100.0f, Game.HEIGHT/3+infoTextHeight);
        levelInfoText.setSize(-1);
        textHandler.addText(levelInfoText);
        infoTextHeight += 20.0f;
    }
    
    /**
     * Check if position (x, y) is allowed for spawning. This returns false
     * if position is not near the borders of the window.
     * 
     * @param x
     * @param y
     * @return true if spawning fails
     */
    protected boolean spawningFails(float x, float y) {
        if(x > Game.WIDTH*0.3f && x < Game.WIDTH*0.7f) {
            if(y > Game.HEIGHT*0.3f && y < Game.HEIGHT*0.7f) {
                return false;
            }
        }
        return true;
    }

}
