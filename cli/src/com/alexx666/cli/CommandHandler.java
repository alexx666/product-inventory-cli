package com.alexx666.cli;

import com.alexx666.core.products.Commands.Domain.ProductRepository;
import com.alexx666.core.products.ProductsCommandHandler;

import java.io.BufferedReader;

public abstract class CommandHandler {

     protected BufferedReader reader;
     protected ProductsCommandHandler handlers;

     public CommandHandler(ProductRepository repository, BufferedReader reader) {
          this.reader = reader;
          this.handlers = new ProductsCommandHandler(repository);
     }

     public abstract void handle() throws Exception;
}
