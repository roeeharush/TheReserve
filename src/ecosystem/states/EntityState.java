package ecosystem.states;

import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;

/**
 * ממשק אבסטרקטית המגדיר את החוזה עבור מצבי ההתנהגות השונים של הישויות החיות במערכת
 * הממשק מהווה את הבסיס למכונת המצבים הפולימורפית ומאפשר לשנות את התנהגות היצור בזמן ריצה בהתאם למצבו הפיזיולוגי או המיקום שלו במפה
 * Pattern: State
 */

public interface EntityState {
    void doAction(LivingEntity e, Environment env);
    boolean canMove();
}
