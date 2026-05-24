import ecosystem.core.Environment;
import ecosystem.core.SimulationEngine;
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