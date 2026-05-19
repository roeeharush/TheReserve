import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.animals.Deer;
import ecosystem.entities.animals.Lion;
import ecosystem.entities.animals.Rabbit;
import ecosystem.entities.plants.Flower;
import ecosystem.entities.plants.OakTree;
import ecosystem.entities.resources.Rock;
import ecosystem.entities.resources.Water;
import ecosystem.gui.ImageLoader;        // ← חדש
import ecosystem.gui.SimulationView;     // ← חדש

public class Main {
    public static void main(String[] args) {

        // טוענים את התמונות פעם אחת
        ImageLoader.loadImage(); // ← חדש

        int rows = 10;
        int cols = 10;
        Environment env = new Environment(rows, cols);

        env.addEntity(new Lion(new Position(0, 0)));
        env.addEntity(new Lion(new Position(1, 0)));
        env.addEntity(new Lion(new Position(2, 0)));
        env.addEntity(new Deer(new Position(0, 3)));
        env.addEntity(new Deer(new Position(1, 3)));
        env.addEntity(new Deer(new Position(2, 3)));
        env.addEntity(new Rabbit(new Position(0, 6)));
        env.addEntity(new Rabbit(new Position(6, 6)));
        env.addEntity(new Rabbit(new Position(5, 6)));
        env.addEntity(new Flower(new Position(7, 6)));
        env.addEntity(new Flower(new Position(1, 4)));
        env.addEntity(new Flower(new Position(2, 2)));
        env.addEntity(new OakTree(new Position(3, 6)));
        env.addEntity(new OakTree(new Position(8, 8)));
        env.addEntity(new OakTree(new Position(7, 5)));
        env.addEntity(new Rock(new Position(4, 6)));
        env.addEntity(new Rock(new Position(9, 0)));
        env.addEntity(new Rock(new Position(5, 8)));
        env.addEntity(new Water(new Position(7, 1)));
        env.addEntity(new Water(new Position(8, 2)));
        env.addEntity(new Water(new Position(9, 8)));

        // פותחים את החלון הגרפי ← חדש
        new SimulationView(env);
    }
}