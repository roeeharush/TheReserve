package ecosystem.core;

import ecosystem.commands.WorldCommand;
import ecosystem.entities.AbstractEntity;
import ecosystem.interfaces.Actable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;


/**
 * מחלקה שאחראית להריץ את כל הסימולציה צעד אחרי צעד
 * המנוע הזה הוא המוח שעובר על כל הישויות מעדכן אותן ומנקה את אלה שמתו מהמפה
 */
public class SimulationEngine {
    private final Environment environment;
    private final BlockingQueue<WorldCommand> commandQueue = new LinkedBlockingQueue<>();
    private final List<EntityThread> threads = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(SimulationEngine.class.getName());

    /**
     * בונה מנוע סימולציה חדש ומחבר אותו לסביבת העולם שנוצרה
     *
     * @param environment העולם שבו המנוע הולך לעבוד ולנהל את היצורים
     */
    public SimulationEngine(Environment environment) {
        this.environment = environment;
    }

    public void startAllThreads() {
        for (AbstractEntity e : environment.getEntities()) {
            if (e instanceof Actable) {
                logger.info("Thread start: " + e.getClass().getSimpleName());
                EntityThread t = new EntityThread((Actable) e, environment, commandQueue);
                threads.add(t);
                t.start();
            }
        }
    }

    public void stopAllThreads() {
        for (EntityThread t : threads) {
            t.stopThread();
        }
        threads.clear();
        commandQueue.clear();
    }


    /**
     * מבצע פעימה אחת של זמן בתוך המערכת
     * המנוע מפעיל את כל היצורים שיודעים לפעול מוריד מהמפה את אלה שנגמר להם הכוח ומדפיס סטטיסטיקה על כמה חיות וצמחים נשארו בחיים בסוף התור
     */
    public void Tick() {

        for (AbstractEntity e : environment.getEntities()) {
            if (e instanceof Actable)
                ((Actable) e).act(this.environment);
        }

        List<WorldCommand> group = new ArrayList<>();
        commandQueue.drainTo(group);
        for (WorldCommand cmd : group) {
            boolean success = cmd.execute(environment);
            if (success) {
                logger.info("Has done " + cmd.getClass().getSimpleName());
            }
        }

        List<AbstractEntity> toRemove = new ArrayList<>();
        for (AbstractEntity e : environment.getEntities()) {
            if (!e.isAlive())
                toRemove.add(e);
        }
        for (AbstractEntity e : toRemove) {
            environment.removeEntity(e);
            logger.info("Removed: " + e.getClass().getSimpleName() + " In Position " + e.getPosition());
        }
        logger.info("Tick " + environment.getTicks() + " Finished ");


        for (AbstractEntity e : environment.getEntities()) {
            if (e instanceof Actable) {
                boolean hasThread = false;
                for (EntityThread t : threads) {
                    if (t.getEntity() == e) {
                        hasThread = true;
                        break;
                    }
                }
                if (!hasThread) {
                    EntityThread t = new EntityThread(
                            (Actable) e, environment, commandQueue);
                    threads.add(t);
                    t.start();
                }
            }
        }
        environment.nextTick();
        environment.notifyObservers();
    }
}

