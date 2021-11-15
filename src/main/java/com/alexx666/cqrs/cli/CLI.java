package com.alexx666.cqrs.cli;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class CLI {
    private final Map<String, CLICommand> commands;
    private final BufferedReader reader;

    private CLI(Builder builder) {
        this.commands = builder.commands;
        this.reader = builder.reader;
    }

    private CLICommand getCommandForAction(String action) {
        if (!this.commands.containsKey(action)) {
            throw new IllegalArgumentException("Unknown command " + action);
        }
        return this.commands.get(action);
    }

    public void showHelp() {
        System.out.println("Available actions:");

        for (String action: this.commands.keySet()) {
            System.out.println("    - " + action);
        }
    }

    public void run() {
        try {
            System.out.print("Action: ");
            String action = reader.readLine().trim();

            this.getCommandForAction(action).handle(reader);
        } catch (Exception error) {
            error.printStackTrace(System.out);
        }
    }

    public static class Builder {
        private Map<String, CLICommand> commands;
        private BufferedReader reader;

        public Builder() {
            this.commands = new HashMap<>();
        }

        public Builder input(BufferedReader reader) {
            this.reader = reader;
            return this;
        }

        public Builder addCommand(CLICommand command) {
            this.commands.put(command.getName(), command);
            return this;
        }

        public CLI build() {
            return new CLI(this);
        }
    }
}
