package com.alexx666.core.cqrs;

public abstract class Command {

    private final String uuid;

    public Command() {
        this.uuid = Hashing.getRandomHash();
    }

    public String getUUID() {
        return this.uuid;
    }
}
