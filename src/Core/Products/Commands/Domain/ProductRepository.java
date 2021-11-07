package Core.Products.Commands.Domain;

public interface ProductRepository {
    Product find(String productId) throws Exception;
    void save(Product product);
}
