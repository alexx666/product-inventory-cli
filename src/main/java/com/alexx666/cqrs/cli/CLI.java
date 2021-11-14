package com.alexx666.cqrs.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CLI {
    private final Map<String, CLICommand> commands;
    private final BufferedReader reader;

    private CLI(Builder builder) {
        this.commands = builder.commands;
        this.reader = builder.reader;
    }

    public CLICommand getHandler(String command) {
        if (!this.commands.containsKey(command)) {
            throw new IllegalArgumentException("Unknown command " + command);
        }
        return this.commands.get(command);
    }

    public Set<String> availableActions() {
        return this.commands.keySet();
    }

    public void start() throws IOException {
        boolean shouldContinue = true;

        System.out.println("Available actions:");

        for (String action: this.availableActions()) {
            System.out.println("    - " + action);
        }

        while (shouldContinue) {
            try {
                System.out.print("Action: ");
                String command = reader.readLine().trim();

                CLICommand cliCommand = this.getHandler(command);

                cliCommand.handle();
            } catch (Exception error) {
                error.printStackTrace();
                System.out.println();
            } finally {
                System.out.print("Continue with a new action? (Y/n): ");
                shouldContinue = !reader.readLine().equalsIgnoreCase("n");
            }
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

        public Builder addCommand(String command, CLICommand handler) {
            this.commands.put(command, handler);
            return this;
        }

        public CLI build() {
            return new CLI(this);
        }
    }
}
