# DDoS Attack Detection

A simple Java program that detects Distributed Denial-of-Service (DDoS) attacks using **Shannon entropy** computed over simulated network traffic. It simulates both normal and DDoS traffic, measures how "spread out" the source IP addresses are, and flags traffic as an attack when that spread drops below a threshold — then reports the detector's accuracy over repeated trials.

## How It Works

A DDoS attack typically comes from a small number of source IPs sending a flood of traffic to one destination, whereas normal traffic comes from a wide variety of source IPs. This difference in *randomness* can be measured using entropy:

- **Normal traffic** → source IPs are diverse → **high entropy**
- **DDoS traffic** → most packets share the same (attacker) source IP → **low entropy**

The detector computes the Shannon entropy of the source-IP distribution in a batch of packets and compares it against a threshold. If entropy falls below the threshold, the traffic is flagged as a potential DDoS attack.

```
H(X) = -Σ p(x) * log2(p(x))
```

where `p(x)` is the proportion of packets coming from source IP `x`.

## Project Structure

| File | Responsibility |
|---|---|
| `Packet.java` | Simple data model representing a network packet (`srcIP`, `dstIP`, `size`). |
| `Utils.java` | Utility for generating random IPv4 addresses (used to simulate diverse normal traffic). |
| `TrafficSimulator.java` | Generates a batch of simulated packets — either normal traffic (random source IPs) or DDoS traffic (a single repeated source IP). |
| `EntropyCalculator.java` | Computes the Shannon entropy of a list of source IPs. |
| `DDoSDetector.java` | Runs entropy calculation on a batch of traffic and classifies it as an attack or normal, based on a configurable threshold. |
| `AccuracyTest.java` | Runs the detector over 50 simulated normal batches and 50 simulated DDoS batches, and reports overall detection accuracy. Contains the `main` entry point. |

## Requirements

- Java JDK 8 or later (no external dependencies, no build tool required)

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Deepshikha-Mohanty/DDoS-Attack-Detection.git
   cd DDoS-Attack-Detection
   ```

2. Compile all source files:
   ```bash
   javac *.java
   ```

3. Run the accuracy test:
   ```bash
   java AccuracyTest
   ```

### Sample Output

```
Entropy: 8.9124
✅ Normal Traffic.
Entropy: 0.0000
⚠️ Potential DDoS Attack Detected!
...
Detection Accuracy: 96.00%
```

(Actual entropy values and accuracy will vary slightly across runs due to randomized traffic simulation.)

## Configuration

- **Entropy threshold** — set in `AccuracyTest.java` (default `3.5`) when calling `DDoSDetector.detectDDoS(traffic, threshold)`. Lower the threshold to make the detector more tolerant of borderline traffic; raise it to make detection stricter.
- **Packets per batch** — controlled by the `numPackets` argument to `TrafficSimulator.simulateTraffic(...)` (default `1000`).
- **Number of test trials** — controlled by the loop counts in `AccuracyTest.testModel()` (default 50 normal + 50 DDoS batches).

## Notes / Limitations

- Traffic is **simulated**, not captured from a live network — `TrafficSimulator` generates random source IPs for normal traffic and a single fixed source IP for DDoS traffic, rather than reading real packet captures.
- The detector only considers **source IP distribution**; a more robust real-world detector would also consider packet rate, destination port distribution, protocol type, and time-windowed analysis.
- The entropy threshold is fixed and manually tuned rather than learned from data.

## Possible Extensions

- Feed in real packet captures (e.g. via `pcap4j` or parsed `.pcap` files) instead of simulated traffic.
- Make the threshold adaptive based on a rolling baseline of "normal" entropy.
- Add additional signals (packet rate, destination-port entropy, protocol distribution) for a more robust classifier.
- Visualize entropy over time to see attacks as they develop.
