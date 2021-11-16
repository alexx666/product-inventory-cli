package com.alexx666.cqrs.products.presenters;

import com.alexx666.core.cleanarch.View;
import com.alexx666.cqrs.cli.CLICommand;

import java.util.Collection;
import java.util.Map.Entry;

public class HelpConsoleView implements View<Entry<String, CLICommand>> {
    @Override
    public void present(Entry<String, CLICommand> object) {
        // TODO: implement
    }

    @Override
    public void present(Collection<Entry<String, CLICommand>> objects) {
        System.out.println("Available actions:");

        for (Entry<String, CLICommand> entry: objects) {
            System.out.println("    - " + entry.getKey());
        }
    }
}
