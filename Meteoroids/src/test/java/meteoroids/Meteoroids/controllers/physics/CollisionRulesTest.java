package meteoroids.Meteoroids.controllers.physics;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.BoundingSphere;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.BasicProjectile;

import org.junit.Before;
import org.junit.Test;

public class CollisionRulesTest {

    private CollisionRules rules;
    private BoundingSphere bsA;
    private BoundingSphere bsB;
    private BoundingSphere bsC;
    private BoundingSphere bsD;
    private List<GameObject> killed;
    
    @Before
    public void setUp() {
        killed = new ArrayList<>();
        rules = new CollisionRules(killed);
        
        bsA = new BasicProjectile(0.0f, 0.0f);
        bsB = new Ship(10.0f, 15.0f);
        bsC = new Asteroid(10.0f, 10.0f);
        bsD = new Planet(10.0f, 10.0f);
    }
    
    @Test
    public void testCheckBulletToShipHit() {
        assertTrue(rules.checkBulletToShipHit(bsA, bsB));
        assertTrue(rules.checkBulletToShipHit(bsB, bsA));
        assertTrue(!rules.checkBulletToShipHit(bsA, bsC));
        assertTrue(!rules.checkBulletToShipHit(bsA, bsD));
        assertTrue(!rules.checkBulletToShipHit(bsB, bsC));
        assertTrue(!rules.checkBulletToShipHit(bsB, bsD));
    }
    
    @Test
    public void testCheckBulletToBulletHit() {
        assertTrue(rules.checkBulletToBulletHit(bsA, bsA));
        assertTrue(!rules.checkBulletToBulletHit(bsB, bsA));
        assertTrue(!rules.checkBulletToBulletHit(bsA, bsC));
        assertTrue(!rules.checkBulletToBulletHit(bsA, bsD));
        assertTrue(!rules.checkBulletToBulletHit(bsB, bsC));
        assertTrue(!rules.checkBulletToBulletHit(bsB, bsD));
    }
    
    @Test
    public void testCheckAsteroidToAsteroidHit() {
        assertTrue(rules.checkAsteroidToAsteroidHit(bsC, bsC));
        assertTrue(!rules.checkAsteroidToAsteroidHit(bsB, bsA));
        assertTrue(!rules.checkAsteroidToAsteroidHit(bsA, bsC));
        assertTrue(!rules.checkAsteroidToAsteroidHit(bsA, bsD));
        assertTrue(!rules.checkAsteroidToAsteroidHit(bsB, bsC));
        assertTrue(!rules.checkAsteroidToAsteroidHit(bsB, bsD));
    }
    
    @Test
    public void testCheckAsteroidToPlanetHit() {
        assertTrue(rules.checkAsteroidToPlanetHit(bsC, bsD));
        assertTrue(rules.checkAsteroidToPlanetHit(bsD, bsC));
        assertTrue(!rules.checkAsteroidToPlanetHit(bsA, bsC));
        assertTrue(!rules.checkAsteroidToPlanetHit(bsA, bsD));
        assertTrue(!rules.checkAsteroidToPlanetHit(bsB, bsC));
        assertTrue(!rules.checkAsteroidToPlanetHit(bsB, bsD));
        assertTrue("Asteroid should be killed.", bsC == (Asteroid)killed.get(0));
    }
    
    @Test
    public void testCheckBulletToAsteroidHit() {
        assertTrue(rules.checkBulletToAsteroidHit(bsC, bsA));
        assertTrue(rules.checkBulletToAsteroidHit(bsA, bsC));
        assertTrue(!rules.checkBulletToAsteroidHit(bsD, bsC));
        assertTrue(!rules.checkBulletToAsteroidHit(bsA, bsD));
        assertTrue(!rules.checkBulletToAsteroidHit(bsB, bsC));
        assertTrue(!rules.checkBulletToAsteroidHit(bsB, bsD));
        assertTrue("Asteroid should be killed.", bsC == (Asteroid)killed.get(0));
    }

}
