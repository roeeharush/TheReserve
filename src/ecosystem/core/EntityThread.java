package ecosystem.core;
import java.util.List;
import ecosystem.commands.WorldCommand;
import ecosystem.interfaces.Actable;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class EntityThread extends Thread{
    private final Actable entity;
    private final Environment environment;
    private final BlockingQueue<WorldCommand> commandQueue;
    private final Random random = new Random();
    private final AtomicBoolean running = new AtomicBoolean(true);

    public EntityThread(Actable entity, Environment environment,BlockingQueue<WorldCommand> commandQueue) {
        this.entity = entity;
        this.environment = environment;
        this.commandQueue = commandQueue;
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                Thread.sleep(500 + random.nextInt(1000));

                List<WorldCommand> commands = entity.collectCommands(environment);
                for (WorldCommand cmd : commands) {
                    commandQueue.put(cmd);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stopThread() {
        running.set(false);
        interrupt();

    }

    public Actable getEntity() {
        return entity;
    }


}
