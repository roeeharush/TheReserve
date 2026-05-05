package ecosystem.entities.animals;

import ecosystem.interfaces.Eater;
import ecosystem.interfaces.EdibleByCarnivore;
import ecosystem.interfaces.Movable;
import ecosystem.interfaces.Sensory;

public abstract class Animal implements Movable, Eater, Sensory, EdibleByCarnivore {
}
