package com.alexx666.incli.cli;

import java.io.BufferedReader;

public abstract class CLICommand {

     private final String name;

     public CLICommand(String name) {
          this.name = name;
     }

     public abstract void handle(BufferedReader reader) throws Exception;

     public String getName() {
          return name;
     }
}
