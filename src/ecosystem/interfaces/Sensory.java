package ecosystem.interfaces;
import java.util.List;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;

public interface Sensory {
    public List<AbstractEntity> sense(Environment env);
}
