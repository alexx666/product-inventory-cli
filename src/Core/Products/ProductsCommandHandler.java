package Core.Products;

import Core.Products.Commands.Application.AddNewProduct;
import Core.Products.Commands.Domain.Product;
import Core.Products.Commands.Application.RateProduct;
import Core.Products.Commands.Domain.ProductRepository;

public class ProductsCommandHandler {

    private ProductRepository repository;

    public ProductsCommandHandler(ProductRepository repository) {
        this.repository = repository;
    }

    // FIXME: use a response model to represent the result of the operation
    public double handle(RateProduct command) throws Exception {
        Product product = this.repository.find(command.getProductId());

        product.rate(command.getUserId(), command.getRating());

        this.repository.save(product);

        return product.getTotalRating();
    }

    // FIXME: use a response model to represent the result of the operation
    public String handle(AddNewProduct command) {
        Product product = new Product(command.getName());

        this.repository.save(product);

        return product.getProductId();
    }
}
