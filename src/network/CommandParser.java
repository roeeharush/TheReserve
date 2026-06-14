package network;

import ecosystem.core.Environment;

public class CommandParser {
    public static NetworkCommand parse(String message, Environment env) {
        String[] parts = message.split(",");
        String action = parts[0];

        switch (action) {
            case "SPAWN": {
                String type = parts[1];
                double energy = Double.parseDouble(parts[2]);
                int row = Integer.parseInt(parts[3]);
                int col = Integer.parseInt(parts[4]);
                return new SpawnEntityCommand(type, row, col, energy ,env);
            }
            default:
                throw new IllegalArgumentException("פקודה לא מוכרת: " + action);
        }
    }
}
