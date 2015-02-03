package meteoroids.Meteoroids.gameobjects.utilities;

import java.util.ArrayDeque;

import meteoroids.Meteoroids.gameobjects.DrawableGameObject;

import org.lwjgl.opengl.GL11;

/**
 * Thrust flame for flying objects.
 * 
 * @author vpyyhtia
 *
 */
public class ThrustFlame extends DrawableGameObject {

    ArrayDeque<Flame> flames;
    private int counter;
    private int numberOfFlames;

    public ThrustFlame(float x, float y) {
        flames = new ArrayDeque<>();
        counter = 0;
        numberOfFlames = 10;
        for(int i = 0; i < numberOfFlames; i++) {
            flames.push(new Flame(x, y));
        }
    }

    @Override
    public void draw() {
        ArrayDeque<Flame> tmp = new ArrayDeque<>();
        for(int i = 0; i < numberOfFlames; i++) {
            Flame flame = flames.pollLast();
            flame.draw(i);
            tmp.push(flame);
        }
        flames = tmp;
    }

    public void addFlame(float x, float y) {
        counter++;
        if(counter >= 8)
            counter = 0;

        if(counter == 0) {
            flames.push(new Flame(x, y));
            flames.pollLast();
        }
    }
    
    public ArrayDeque<Flame> getFlames() {
        return flames;
    }

    public class Flame {
        private float x;
        private float y;

        public Flame(float x, float y) {
            this.x = x;
            this.y = y;
        }

        void draw(int index) {
            GL11.glColor3f(0.05f * (index + 1), 0.01f * (index + 1), 0.0f);
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(this.x - 1.5f, this.y - 1.5f);
            GL11.glVertex2f(this.x + 1.5f, this.y - 1.5f);
            GL11.glVertex2f(this.x + 1.5f, this.y + 1.5f);
            GL11.glVertex2f(this.x - 1.5f, this.y + 1.5f);
            GL11.glEnd();
        }
        
        public float getX() {
            return x;
        }
        
        public float getY() {
            return y;
        }
    }

    @Override
    public int getID() {
        return -1;
    }
}
