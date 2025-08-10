import java.util.ArrayList;
import java.util.List;

public class DDoSDetector {

    public static boolean detectDDoS(List<Packet> traffic, double threshold) {
        List<String> srcIPs = new ArrayList<>();
        for (Packet packet : traffic) {
            srcIPs.add(packet.srcIP);
        }

        double entropy = EntropyCalculator.calculateEntropy(srcIPs);
        System.out.printf("Entropy: %.4f%n", entropy);

        if (entropy < threshold) {
            System.out.println("⚠️ Potential DDoS Attack Detected!");
            return true;
        } else {
            System.out.println("✅ Normal Traffic.");
            return false;
        }
    }
}
