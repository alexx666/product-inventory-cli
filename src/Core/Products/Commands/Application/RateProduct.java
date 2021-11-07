package Core.Products.Commands.Application;

import Core.Utils.Command;
import Core.Products.Commands.Domain.ProductRating;

public class RateProduct extends Command {

    private String productId;
    private String userId;
    private ProductRating rating;

    public RateProduct(String productId, String userId, int rating) {
        super();

        this.productId = productId;
        this.userId = userId;
        this.rating = ProductRating.create(rating);
    }

    public String getProductId() { return this.productId; }
    public String getUserId() { return this.userId; }
    public ProductRating getRating() { return this.rating; }
}
