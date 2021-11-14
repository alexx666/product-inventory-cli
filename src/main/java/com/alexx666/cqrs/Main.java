package com.alexx666.cqrs;

import com.alexx666.cqrs.products.*;
import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.cqrs.utils.CommandParser;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.ProductsQueryHandler;
import com.alexx666.products.infra.InMemoryProductDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        InMemoryProductDatabase database = new InMemoryProductDatabase.Builder().build();

        CommandParser resolver = new CommandParser()
                .register("rate", RateProductHandler.class)
                .register("add", AddNewProductHandler.class)
                .register("findById", FindByIdHandler.class)
                .register("findRelated", FindRelatedProductsHandler.class)
                .register("findOutOfStock", FindOutOfStockProductsHandler.class)
                .register("findByName", FindByNameHandler.class);

        boolean shouldTerminate = false;

        System.out.println("Available actions:");

        System.out.println("    - rate");
        System.out.println("    - add");
        System.out.println("    - findById");
        System.out.println("    - findRelated");
        System.out.println("    - findOutOfStock");
        System.out.println("    - findByName");

        while (!shouldTerminate) {
            try {

                System.out.print("Action: ");

                String command = reader.readLine();

                boolean isQuery = command.contains("find");

                Class handlerClass = isQuery
                        ? ProductsQueryHandler.class
                        : ProductsCommandHandler.class;

                Constructor<? extends CommandHandler> constructor = resolver
                        .getHandler(command)
                        .getConstructor(handlerClass, BufferedReader.class);

                CommandHandler parser = isQuery
                        ? constructor.newInstance(new ProductsQueryHandler(database), reader)
                        : constructor.newInstance(new ProductsCommandHandler(database), reader);

                parser.handle();

                System.out.print("Continue with a new action? (Y/n): ");

                shouldTerminate = reader.readLine().equalsIgnoreCase("n");
            } catch (Exception error) {
                System.out.println(error);
                error.printStackTrace();
            }
        }
    }
}
