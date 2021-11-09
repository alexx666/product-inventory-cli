package com.alexx666.cqrs;

import com.alexx666.cli.CommandHandler;
import com.alexx666.cli.CommandParser;

import com.alexx666.core.products.ProductsCommandHandler;
import com.alexx666.cqrs.products.AddNewProductHandler;
import com.alexx666.core.products.Commands.Domain.ProductRepository;
import com.alexx666.core.products.Commands.Infrastructure.InMemoryProductRepository;
import com.alexx666.cqrs.products.RateProductHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ProductRepository repository = new InMemoryProductRepository(new HashMap<>());
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
