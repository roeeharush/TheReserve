package ecosystem.interfaces;

import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * ממשק שמגדיר את היכולת של ישות לבצע פעולה בעולם שלנו
 * כל מחלקה שמממשת את הממשק הזה חייבת להגדיר מה היא עושה בכל תור של הסימולציה
 */
public interface Actable {

    public boolean act(Environment env);
    default List<WorldCommand> collectCommands(Environment env) {
        return new ArrayList<>();
    }
}
