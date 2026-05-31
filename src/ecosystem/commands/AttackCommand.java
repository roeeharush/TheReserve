package ecosystem.commands;

import ecosystem.core.Environment;
import ecosystem.entities.animals.Animal;
import ecosystem.interfaces.Consumable;

public class AttackCommand implements WorldCommand{
    private final Animal animal;
    private final Consumable target;

    public AttackCommand(Animal animal , Consumable target){
        this.animal = animal;
        this.target = target;
    }

    @Override
    public boolean execute(Environment env) {
        if (animal == null )
            return false;
        if (target == null)
            return false;
        return animal.eat(target);

    }
}
