package network;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.factory.EntityFactory;


public class SpawnEntityCommand implements NetworkCommand {
    private final Environment environment;
    private final String type;
    private final int row;
    private final int col;
    private final double energy;

    public SpawnEntityCommand(String type, int row, int col, double energy ,Environment env) {
        this.type = type;
        this.row = row;
        this.col = col;
        this.energy = energy;
        this.environment = env;
    }

    @Override
    public void execute() {
        AbstractEntity entity = EntityFactory.createEntity(type, new Position(row, col), energy);
        environment.addEntity(entity);
    }
}
