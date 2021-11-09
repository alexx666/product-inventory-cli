package com.alexx666.core.utils;

public abstract class Command {

    private String uuid;

    public Command() {
        this.uuid = Hashing.getRandomHash();
    }

    public String getUUID() {
        return this.uuid;
    };
}
