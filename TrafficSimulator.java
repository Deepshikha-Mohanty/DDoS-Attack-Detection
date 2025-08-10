
import java.util.*;

public class TrafficSimulator {

    public static List<Packet> simulateTraffic(int numPackets, boolean isDDoS) {
        List<Packet> traffic = new ArrayList<>();

        for (int i = 0; i < numPackets; i++) {
            String srcIP = isDDoS ? "192.168.0.1" : Utils.generateIP();
            String dstIP = "10.0.0.1";
            int size = 40 + new Random().nextInt(1460); // Between 40 and 1500
            traffic.add(new Packet(srcIP, dstIP, size));
        }
        return traffic;
    }
}
