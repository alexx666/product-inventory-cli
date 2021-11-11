package com.alexx666.cqrs;

import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.cqrs.utils.CommandParser;
import com.alexx666.cqrs.products.AddNewProductHandler;
import com.alexx666.cqrs.products.RateProductHandler;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.ProductRepository;
import com.alexx666.products.infra.InMemoryProductRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ProductRepository repository = new InMemoryProductRepository();
        ProductsCommandHandler handlers = new ProductsCommandHandler(repository);

        CommandParser resolver = new CommandParser()
                .register("rate", RateProductHandler.class)
                .register("add", AddNewProductHandler.class);

        while (true) {
            try {
                System.out.print("Action (add/rate): ");
                String command = reader.readLine();

                Constructor<? extends CommandHandler> constructor = resolver
                        .getHandler(command)
                        .getConstructor(ProductsCommandHandler.class, BufferedReader.class);

                CommandHandler parser = constructor.newInstance(handlers, reader);

                parser.handle();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
    }
}
