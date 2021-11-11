package com.alexx666.cqrs.utils;

import java.io.BufferedReader;

public abstract class CommandHandler {

     protected BufferedReader reader;

     public CommandHandler(BufferedReader reader) {
          this.reader = reader;
     }

     public abstract void handle() throws Exception;
}
