package ecosystem.entities;
import ecosystem.core.Position;

public class LivingEntity extends AbstractEntity {

    int maxEnergy;
    int energy;
    int age;




    public LivingEntity(Position position, char symbol, boolean alive) {
        super(position, symbol, alive);
    }
}
