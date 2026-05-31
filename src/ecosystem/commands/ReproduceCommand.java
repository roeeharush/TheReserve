package ecosystem.commands;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;

public class ReproduceCommand  implements WorldCommand{
    private final AbstractEntity newEntity;

    public ReproduceCommand(AbstractEntity newEntity, Position position) {
        this.newEntity = newEntity;
    }

    @Override
    public boolean execute(Environment env) {
        if (newEntity == null)
            return false;
        return env.addEntity(newEntity);
    }
}
