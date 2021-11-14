package com.alexx666.cqrs.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandParser {
    private final Map<String, Class<? extends CommandHandler>> commands;

    private CommandParser(Builder builder) {
        this.commands = builder.commands;
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

    public static class Builder {
        private Map<String, Class<? extends CommandHandler>> commands;

        public Builder() {
            this.commands = new HashMap<>();
        }

        public Builder register(String command, Class<? extends CommandHandler> handler) {
            this.commands.put(command, handler);
            return this;
        }

        public CommandParser build() {
            return new CommandParser(this);
        }
    }
}
