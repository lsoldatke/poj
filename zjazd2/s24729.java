package gamers;

import main.Pair;
import main.PhysicalObject;
import main.Unit;

public class s24729 extends Unit {

    public s24729(String id, Pair<Double, Double> position, double rotate, CollisionModel model) {
        super(id, position, rotate, model);
    }

    @Override
    public void run() {
        this.enableStopOnWall();
        this.enableMovement();

        while(true) {
            // System.out.println("nearest collision: " + this.nearestCollision());
            // System.out.println("rotate tacho count: " + this.getRotateTachoCount());
            // System.out.println("rotation speed: " + this.getRotationSpeed());

            if (whatIsInRange() == 0 && !isRotating()) {
                this.forward();
            }

            if (nearestCollision() <= 3 && (whatIsInRange() == 1 || whatIsInRange() == 2)) {
                this.stop();
                this.rotateBy(180);
                this.forward();
            }

            if (whatIsInRange() == 3 && nearestCollision() <= 2) {
                this.attackInNextMove();
            }
        }
    }
}
