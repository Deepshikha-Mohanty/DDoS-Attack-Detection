public class Packet {
    String srcIP;
    String dstIP;
    int size;

    public Packet(String srcIP, String dstIP, int size) {
        this.srcIP = srcIP;
        this.dstIP = dstIP;
        this.size = size;
    }
}

