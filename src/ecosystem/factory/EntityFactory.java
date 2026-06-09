package ecosystem.factory;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Deer;
import ecosystem.entities.animals.Lion;
import ecosystem.entities.animals.Rabbit;
import ecosystem.entities.plants.Flower;
import ecosystem.entities.plants.OakTree;
import ecosystem.entities.resources.Rock;
import ecosystem.entities.resources.Water;

public class EntityFactory {

    public static AbstractEntity createEntity(String type, Position pos, double initialEnergy) {
        switch (type) {
            case "Lion"    -> { return new Lion(pos, initialEnergy); }
            case "Deer"    -> { return new Deer(pos, initialEnergy); }
            case "Rabbit"  -> { return new Rabbit(pos, initialEnergy); }
            case "Flower"  -> { return new Flower(pos, initialEnergy); }
            case "OakTree" -> { return new OakTree(pos, initialEnergy); }
            case "Water"   -> { return new Water(pos); }
            case "Rock"    -> { return new Rock(pos); }
            default -> throw new IllegalArgumentException("Unknown entity type: " + type);
        }
    }
}
