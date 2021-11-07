import CLI.CommandHandler;
import CLI.CommandParser;
import Core.Products.Commands.Domain.ProductRepository;
import Core.Products.Commands.Infrastructure.InMemoryProductRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class Application {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ProductRepository repository = new InMemoryProductRepository(new HashMap<>());

        CommandParser resolver = new CommandParser();

        while (true) {
            try {
                System.out.print("Action (add/rate): ");
                String command = reader.readLine();

                Constructor<? extends CommandHandler> constructor = resolver.getHandler(command)
                        .getConstructor(ProductRepository.class, BufferedReader.class);

                CommandHandler parser = constructor.newInstance(repository, reader);

                parser.handle();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
    }
}
