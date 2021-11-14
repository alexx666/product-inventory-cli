package com.alexx666.cqrs.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandParser {
    private final Map<String, Class<? extends CommandHandler>> commands;

    public CommandParser() {
        this.commands = new HashMap<>();
    }

    public CommandParser register(String command, Class<? extends CommandHandler> handler) {
        this.commands.put(command, handler);

        return this;
    }

    public Class<? extends CommandHandler> getHandler(String command) {
        if (!this.commands.containsKey(command)) {
            throw new IllegalArgumentException("Unknown command " + command);
        }
        return this.commands.get(command);
    }

    public Set<String> availableActions() {
        return this.commands.keySet();
    }
}
