package meteoroids.Meteoroids.controllers;

import java.util.List;

import meteoroids.Meteoroids.gameobjects.Drawable;

import org.lwjgl.opengl.GL11;

/**
 * Handles drawing all the objects. OpenGL is also handled here.
 * 
 * @author vpyyhtia
 *
 */
public class GraphicsController implements Controller {
	
	private int width;
	private int height;
	
	/**
	 * GraphicsController.
	 * 
	 * @param width of the screen
	 * @param height of the screen
	 */
	public GraphicsController(int width, int height) { 
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void update(float deltaTime) {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
	}
	
	/**
	 * Draw objects to the screen.
	 * 
	 * @param objects list of Drawable objects
	 */
	public void draw(List<Drawable> objects) {
		for(Drawable d : objects) {
			d.draw();
		}
	}

	/**
	 * Initialize OpenGL.
	 * 
	 * @return true
	 */
	public boolean init() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		return true;
	}
}
