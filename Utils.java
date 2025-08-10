import java.util.Random;

public class Utils {
    static Random rand = new Random();

    public static String generateIP() {
        return rand.nextInt(256) + "." + rand.nextInt(256) + "." +
               rand.nextInt(256) + "." + rand.nextInt(256);
    }
}

