package ecosystem.commands;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import java.util.logging.Logger;

public class MoveCommand implements WorldCommand{
    private final AbstractEntity entity;
    private final Position newPosition;
    private static final Logger logger = Logger.getLogger(MoveCommand.class.getName());


    public MoveCommand(AbstractEntity entity, Position newPosition) {
        this.entity = entity;
        this.newPosition = newPosition;
    }

    @Override
    public boolean execute(Environment env) {
        if( newPosition==null || !entity.isAlive())
            return false;
        boolean moved = env.moveEntity(entity, newPosition);
        if (moved) {
            logger.info("Moved: " + entity.getClass().getSimpleName() + " To Position " + newPosition);
        }
        return moved;
    }
}
