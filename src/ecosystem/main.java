package ecosystem;

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

public class main {

    public static void main(String[] args) {
        Environment env = new Environment(10, 10);

        env.addEntity(new Rabbit(new Position(5, 5)));
        env.addEntity(new Rabbit(new Position(5, 6)));

        SimulationEngine engine = new SimulationEngine(env);

        for (int i = 0; i < 3; i++) {
            System.out.println("=== Tick " + i + " ===");
            engine.Tick();
        }
  }
}
