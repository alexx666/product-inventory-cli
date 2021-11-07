package Core.Products.Commands.Application;

import Core.Utils.Command;

public class AddNewProduct extends Command {

    private String name;

    // TODO: convert primitives to value objects
    public AddNewProduct(String name) {
        super();

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
