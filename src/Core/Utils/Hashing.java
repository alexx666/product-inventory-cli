package Core.Utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Hashing {
    public static String getRandomHash() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
