package ecosystem.core;

import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;
import ecosystem.entities.plants.Plant;
import ecosystem.interfaces.Actable;
import java.util.ArrayList;
import java.util.List;


/**
 * מחלקה שאחראית להריץ את כל הסימולציה צעד אחרי צעד
 * המנוע הזה הוא המוח שעובר על כל הישויות מעדכן אותן ומנקה את אלה שמתו מהמפה
 */
public class SimulationEngine {
    private Environment environment;


    /**
     * בונה מנוע סימולציה חדש ומחבר אותו לסביבת העולם שנוצרה
     * @param environment העולם שבו המנוע הולך לעבוד ולנהל את היצורים
     */
    public SimulationEngine(Environment environment){
        this.environment = environment;
    }


    /**
     * מבצע פעימה אחת של זמן בתוך המערכת
     * המנוע מפעיל את כל היצורים שיודעים לפעול מוריד מהמפה את אלה שנגמר להם הכוח ומדפיס סטטיסטיקה על כמה חיות וצמחים נשארו בחיים בסוף התור
     */
    public void Tick(){
        for(AbstractEntity e : environment.getEntities()){
            if(e instanceof Actable)
                ((Actable) e).act(this.environment);
        }

        List<AbstractEntity> toRemove = new ArrayList<>();

        for (AbstractEntity e : environment.getEntities()) {
            if (!e.isAlive())
                toRemove.add(e);
        }
        for (AbstractEntity e : toRemove) {
            environment.removeEntity(e);
        }

        int aliveCount = 0;
        int animalCount = 0;
        int plantCount = 0;

        for (AbstractEntity e : environment.getEntities()) {
            if (e.isAlive()) aliveCount++;
            if (e instanceof Animal) animalCount++;
            if (e instanceof Plant) plantCount++;
        }

        System.out.println("Alive entities: " + aliveCount);
        System.out.println("Animals: " + animalCount);
        System.out.println("Plants: " + plantCount);


        System.out.println("The map of the World ");
        System.out.println( environment.toString());


 }
}
