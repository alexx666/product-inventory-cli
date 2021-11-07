package Core.Utils;

public abstract class Command {

    private String uuid;

    public Command() {
        this.uuid = Hashing.getRandomHash();
    }

    public String getUUID() {
        return this.uuid;
    };
}
