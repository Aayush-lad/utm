package org.firewall.core;

import org.firewall.utils.RuleLoader;
import java.util.List;
import java.util.regex.*;

public class SignatureDetector {

    public static void detectSignature(String packetPayload, byte[] packetData) {
        List<String> rules = RuleLoader.loadRules("src/main/resources/rules/snort.rules");

        for (String rule : rules) {
            String contentPattern = extractContentPattern(rule);
            if (contentPattern != null && Pattern.compile(contentPattern, Pattern.CASE_INSENSITIVE).matcher(packetPayload).find()) {
                System.out.println("ALERT! Rule Matched: " + rule);
                System.out.println("Threat detected: " + rule);
                FirewallManager.blockPacket(packetData);
                break; // Stop checking after first match
            }
        }
    }

    private static String extractContentPattern(String rule) {
        Pattern pattern = Pattern.compile("content:\"(.*?)\";");
        Matcher matcher = pattern.matcher(rule);
        return matcher.find() ? matcher.group(1) : null;
    }
}
