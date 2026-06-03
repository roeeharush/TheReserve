package ecosystem.commands;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import java.util.logging.Logger;

public class ReproduceCommand  implements WorldCommand{
    private final AbstractEntity newEntity;
    private static final Logger logger = Logger.getLogger(ReproduceCommand.class.getName());

    public ReproduceCommand(AbstractEntity newEntity) {
        this.newEntity = newEntity;
    }

    @Override
    public boolean execute(Environment env) {
        if (newEntity == null)
            return false;
        boolean added = env.addEntity(newEntity);
        if (added) {
            logger.info("Created: " + newEntity.getClass().getSimpleName()
                    + " In Position " + newEntity.getPosition());
        }
        return added;
    }
}

