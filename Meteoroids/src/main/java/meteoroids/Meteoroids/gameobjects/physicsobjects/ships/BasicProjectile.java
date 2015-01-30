package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

import org.lwjgl.opengl.GL11;

/**
 * Basic projectile for ship's weapon.
 * 
 * @author vpyyhtia
 *
 */
public class BasicProjectile extends Projectile {

    protected float startSpeed;
    protected int killOffTime;
    protected float radius;

    public BasicProjectile(float posX, float posY) {
        super(posX, posY);
        this.startSpeed = 10.0f;
        this.killOffTime = 5000;
        this.radius = 2.0f;
        this.type = ProjectileType.BASIC_PROJECTILE;
    }

    @Override
    public void update(float deltaTime) {
        // Calculate position with delta time
        Vector2f positionAdd = (Vector2f)velocity.clone();
        positionAdd.scale(deltaTime);
        position.add(positionAdd);

        killOffTime -= deltaTime;

        if(killOffTime < 0) {
            this.die();
        }
    }

    @Override
    public Projectile getProjectile(Vector2f position, Vector2f velocity, Vector2f orientation) {
        Projectile projectile = this.clone(position);

        velocity.add(orientation);
        orientation.scale(0.03f * startSpeed * velocity.length());
        projectile.setVelocity(orientation);

        return projectile;
    }

    @Override
    public void draw() {
        GL11.glPushMatrix();
        GL11.glColor3f(1.0f, 0.2f, 0.2f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(this.position.x - radius, this.position.y - radius);
        GL11.glVertex2f(this.position.x + radius, this.position.y - radius);
        GL11.glVertex2f(this.position.x + radius, this.position.y + radius);
        GL11.glVertex2f(this.position.x - radius, this.position.y + radius);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public Projectile clone(Vector2f position) {
        BasicProjectile projectile = new BasicProjectile(position.x, position.y);
        return projectile;
    }

}
