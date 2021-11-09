package CLI;

import Core.Products.Commands.Domain.ProductRepository;
import Core.Products.ProductsCommandHandler;

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
