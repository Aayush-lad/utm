package org.firewall.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PacketAnalyzer {

    public static void analyzePacket(byte[] packetData) {
        if (packetData == null || packetData.length == 0) {
            return; // Ignore empty packets
        }

        // Extract packet payload
        String payload = extractPayload(packetData);
        System.out.println("Captured Packet Payload: " + payload);

        // Send the extracted payload for signature detection
        SignatureDetector.detectSignature(payload, packetData);
    }

    private static String extractPayload(byte[] packetData) {
        try {
            // Convert raw bytes to a readable string for analysis
            return new String(packetData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error extracting payload";
        }
    }
}
