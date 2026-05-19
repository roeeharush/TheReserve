import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.core.SimulationEngine;
import ecosystem.entities.animals.Deer;
import ecosystem.entities.animals.Lion;
import ecosystem.entities.animals.Rabbit;
import ecosystem.entities.plants.Flower;
import ecosystem.entities.plants.OakTree;
import ecosystem.entities.resources.Rock;
import ecosystem.entities.resources.Water;
import ecosystem.gui.ImageLoader;
import ecosystem.gui.SimulationController;
import ecosystem.gui.SimulationView;

public class Main {
    public static void main(String[] args) {

        ImageLoader.loadImage();

        Environment env = new Environment(10, 10);


        SimulationEngine engine = new SimulationEngine(env);
        SimulationView view = new SimulationView(env);
        new SimulationController(view, view.getControlPanel(), env, engine);
    }
}