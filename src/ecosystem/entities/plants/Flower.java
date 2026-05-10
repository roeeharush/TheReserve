package ecosystem.entities.plants;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;

import java.util.Random;

public class Flower extends Plant {
    private static final double initialEnergy = 10.0;
    private static final double maxEnergy = 70.0;
    private static final double growRate = 5.0;
    private static final double reproductionChance = 0.2;

    public Flower(Position position) {
        super(position, 'F', true, initialEnergy, maxEnergy, growRate, reproductionChance);
    }


    @Override
    public boolean onConsumed() {
        this.setAlive(false);
        return true;
    }


    @Override
    public boolean reproduce(Environment env) {
        Random rand = new Random();
        if (rand.nextDouble() <= 0.20) {
            int childrenToCreate = rand.nextInt(3) + 1;
            int createdCount = 0;
            Position myPos = this.getPosition();

            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    Position p = new Position(myPos.getRow() + i, myPos.getCol() + j);

                    int dist = myPos.distanceTo(p);
                    if (dist > 0 && dist <= 2 && env.isPositionFree(p)) {
                        Flower flower = new Flower(p);
                        env.addEntity(flower);
                        createdCount++;

                        if (createdCount == childrenToCreate)
                            return true;
                    }
                }
            }
            return createdCount > 0;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Flower))
            return false;
        return super.equals(o);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}

