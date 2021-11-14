package com.alexx666.cqrs;

import com.alexx666.cqrs.products.*;
import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.cqrs.utils.CommandParser;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.infra.InMemoryProductDatabase;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) throws IOException {

        boolean shouldContinue = true;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        InMemoryProductDatabase database = new InMemoryProductDatabase.Builder().build();

        CommandParser commandParser = new CommandParser.Builder()
                .register("rate", RateProductHandler.class)
                .register("create", AddNewProductHandler.class)
                .register("add", AddToInventoryHandler.class)
                .register("findById", FindByIdHandler.class)
                .register("findRelated", FindRelatedProductsHandler.class)
                .register("findOutOfStock", FindOutOfStockProductsHandler.class)
                .register("findByName", FindByNameHandler.class)
                .build();

        System.out.println("Available actions:");

        for (String action: commandParser.availableActions()) {
            System.out.println("    - " + action);
        }

        while (shouldContinue) {
            try {
                System.out.print("Action: ");
                String command = reader.readLine().trim();

                boolean isQuery = command.contains("find");

                Class handlerClass = isQuery
                        ? ProductsDAO.class
                        : ProductsCommandHandler.class;

                Constructor<? extends CommandHandler> constructor = commandParser
                        .getHandler(command)
                        .getConstructor(handlerClass, BufferedReader.class);

                CommandHandler parser = isQuery
                        ? constructor.newInstance(database, reader)
                        : constructor.newInstance(new ProductsCommandHandler(database), reader);

                parser.handle();

            } catch (Exception error) {
                error.printStackTrace();
                System.out.println();
            } finally {
                System.out.print("Continue with a new action? (Y/n): ");
                shouldContinue = !reader.readLine().equalsIgnoreCase("n");
            }
        }
    }
}
