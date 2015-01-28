package meteoroids.Meteoroids.gameobjects;

import java.util.ArrayDeque;

import org.lwjgl.opengl.GL11;

/**
 * Ship's thrust flame.
 * 
 * @author vpyyhtia
 *
 */
public class ThrustFlame implements Drawable {

    ArrayDeque<Flame> flames;
    private int counter;
    private int numberOfFlames;

    public ThrustFlame(float x, float y) {
        flames = new ArrayDeque<>();
        counter = 0;
        numberOfFlames = 10;
        for (int i = 0; i < numberOfFlames; i++) {
            flames.push(new Flame(x, y));
        }
    }

    @Override
    public void draw() {
        ArrayDeque<Flame> tmp = new ArrayDeque<>();
        for (int i = 0; i < numberOfFlames; i++) {
            Flame flame = flames.pollLast();
            flame.draw(i);
            tmp.push(flame);
        }
        flames = tmp;
    }

    public void addFlame(float x, float y) {
        counter++;
        if (counter >= 8)
            counter = 0;

        if (counter == 0) {
            flames.push(new Flame(x, y));
            flames.pollLast();
        }
    }

    class Flame {
        private float x;
        private float y;

        public Flame(float x, float y) {
            this.x = x;
            this.y = y;
        }

        void draw(int index) {
            GL11.glColor3f(0.04f * index, 0.01f * index, 0.01f * index);
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(this.x - 1.5f, this.y - 1.5f);
            GL11.glVertex2f(this.x + 1.5f, this.y - 1.5f);
            GL11.glVertex2f(this.x + 1.5f, this.y + 1.5f);
            GL11.glVertex2f(this.x - 1.5f, this.y + 1.5f);
            GL11.glEnd();
        }
    }
}
