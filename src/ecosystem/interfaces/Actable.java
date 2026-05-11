package ecosystem.interfaces;

import ecosystem.core.Environment;

/**
 * ממשק שמגדיר את היכולת של ישות לבצע פעולה בעולם שלנו
 * כל מחלקה שמממשת את הממשק הזה חייבת להגדיר מה היא עושה בכל תור של הסימולציה
 */
public interface Actable {

    public boolean act(Environment env);
}
