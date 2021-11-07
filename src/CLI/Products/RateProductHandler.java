package CLI.Products;

import CLI.CommandHandler;

import Core.Products.Commands.Application.RateProduct;
import Core.Products.Commands.Domain.ProductRepository;

import java.io.BufferedReader;

public class RateProductHandler extends CommandHandler {
    public RateProductHandler(ProductRepository repository, BufferedReader reader) {
        super(repository, reader);
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Product Id: ");
        String productId = this.reader.readLine().trim();

        System.out.print("User Id: ");
        String userID = this.reader.readLine().trim();

        System.out.print("Rating: ");
        int rating = Integer.parseInt(this.reader.readLine().trim());

        RateProduct command = new RateProduct(productId, userID, rating);

        System.out.println("Executing request ID: " + command.getUUID());

        double totalRating = handlers.handle(command);

        System.out.println("Total rating: " + totalRating);
    }
}
