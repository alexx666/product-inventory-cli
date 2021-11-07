package CLI;

import CLI.Products.AddNewProductHandler;
import CLI.Products.RateProductHandler;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String, Class<? extends CommandHandler>> commands;

    public CommandParser() {
        this.commands = new HashMap<>();

        this.commands.put("rate", RateProductHandler.class);
        this.commands.put("add", AddNewProductHandler.class);
    }

    public Class<? extends CommandHandler> getHandler(String commandName) {
        return this.commands.get(commandName);
    }
}
