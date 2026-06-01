package ecosystem.commands;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;
import ecosystem.interfaces.Consumable;
import java.util.logging.Logger;

public class AttackCommand implements WorldCommand{
    private final Animal animal;
    private final Consumable target;
    private static final Logger logger = Logger.getLogger(AttackCommand.class.getName());

    public AttackCommand(Animal animal , Consumable target){
        this.animal = animal;
        this.target = target;
    }

    @Override
    public boolean execute(Environment env) {
        if (animal == null || target == null || !animal.isAlive() )
            return false;
        if (!target.isAlive())
            return false;
        boolean ate = animal.eat(target);
        if (ate) {
            logger.info("Ate: " + animal.getClass().getSimpleName() + " Ate " + ((AbstractEntity) target).getClass().getSimpleName());
        }
        return ate;
    }
}
