package com.alexx666.cqrs.cli;

import java.io.BufferedReader;

public abstract class CLICommand {

     protected BufferedReader reader;

     public CLICommand(BufferedReader reader) {
          this.reader = reader;
     }

     public abstract void handle() throws Exception;
}
