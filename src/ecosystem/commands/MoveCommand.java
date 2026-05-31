package ecosystem.commands;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;

public class MoveCommand implements WorldCommand{
    private final AbstractEntity entity;
    private final Position newPosition;


    public MoveCommand(AbstractEntity entity, Position newPosition) {
        this.entity = entity;
        this.newPosition = newPosition;
    }

    @Override
    public boolean execute(Environment env) {
        if( newPosition==null || !entity.isAlive())
            return false;
        return env.moveEntity(entity,newPosition);
    }
}
