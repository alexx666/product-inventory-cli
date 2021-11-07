package Core.Products.Commands.Infrastructure;

import Core.Products.Commands.Domain.Product;
import Core.Products.Commands.Domain.ProductRepository;
import Core.Utils.Hashing;

import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {

    private Map<String, Product> products;

    public InMemoryProductRepository(Map<String, Product> products) {
        this.products = products;
    }

    @Override
    public Product find(String productId) throws Exception {

        Product product = this.products.get(productId);

        if (product == null) {
            throw new Exception("Error: Product " + productId + " not found!");
        }

        return this.products.get(productId);
    }

    @Override
    public void save(Product product) {
        boolean isNew = product.getProductId() == null;

        String productId = isNew ? Hashing.getRandomHash() : product.getProductId();

        product.setProductId(productId);

        this.products.put(productId, product);
    }
}
