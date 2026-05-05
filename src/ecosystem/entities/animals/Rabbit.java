package ecosystem.entities.animals;

import ecosystem.core.Environment;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.Reproducible;

public class Rabbit extends Animal implements Reproducible {
    @Override
    public boolean eat(Consumable target) {
        return false;
    }

    @Override
    public boolean move(Environment env) {
        return false;
    }

    @Override
    public boolean reproduce(Environment env) {
        return false;
    }
}
