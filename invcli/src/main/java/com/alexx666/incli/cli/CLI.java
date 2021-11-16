package com.alexx666.incli.cli;

import com.alexx666.incli.products.presenters.HelpConsoleView;

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

    public void run() {
        try {
            System.out.print("Action: ");
            String action = reader.readLine().trim();

            this.getCommandForAction(action).handle(reader);
        } catch (Exception error) {
            error.printStackTrace(System.out);
        }
    }

    public static final class Builder {
        private final Map<String, CLICommand> commands;
        private BufferedReader reader;

        public Builder() {
            this.commands = new HashMap<>();

            this.addCommand(new CLICommand("help") {
                @Override
                public void handle(BufferedReader reader) {
                    new HelpConsoleView().present(commands.entrySet());
                }
            });
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
            if (this.reader == null) {
                throw new IllegalArgumentException("Input reader not provided!");
            }
            return new CLI(this);
        }
    }
}
