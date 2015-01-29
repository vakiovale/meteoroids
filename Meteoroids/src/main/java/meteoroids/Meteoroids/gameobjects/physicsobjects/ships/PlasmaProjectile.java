package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

import org.lwjgl.opengl.GL11;

public class PlasmaProjectile extends BasicProjectile {

    public PlasmaProjectile(float posX, float posY) {
        super(posX, posY);
        this.startSpeed = 7.0f;
        this.radius = 1.0f;
        this.coolOffTime = 500;
        this.type = ProjectileType.PLASMA_PROJECTILE;
    }
    
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.radius += deltaTime*0.01f;
    }
    
    @Override
    public void draw() {        
        GL11.glPushMatrix();
        GL11.glColor3f(1.0f/radius, 0.2f*radius*0.1f, 0.2f);
        GL11.glTranslatef(this.position.x, this.position.y, 0);
        GL11.glScalef(radius, radius, 1);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2f(0, 0);
        for(int i = 0; i <= 64; i++){
            double angle = Math.PI * 2 * i / 64;
            GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    @Override
    public Projectile clone(Vector2f position) {
        PlasmaProjectile projectile = new PlasmaProjectile(position.x, position.y);
        return projectile;
    }
}
