import java.util.List;

public class AccuracyTest {

    public static void testModel() {
        int correct = 0;
        int total = 100;

        // 50 normal + 50 DDoS
        for (int i = 0; i < 50; i++) {
            List<Packet> normalTraffic = TrafficSimulator.simulateTraffic(1000, false);
            boolean result = DDoSDetector.detectDDoS(normalTraffic, 3.5);
            if (!result) correct++;
        }

        for (int i = 0; i < 50; i++) {
            List<Packet> ddosTraffic = TrafficSimulator.simulateTraffic(1000, true);
            boolean result = DDoSDetector.detectDDoS(ddosTraffic, 3.5);
            if (result) correct++;
        }

        double accuracy = (double) correct / total;
        System.out.printf("\n Detection Accuracy: %.2f%%\n", accuracy * 100);
    }

    public static void main(String[] args) {
        testModel();
    }
}

