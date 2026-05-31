package ecosystem.commands;
import ecosystem.core.Environment;

public interface WorldCommand {
    boolean execute(Environment env);
}
