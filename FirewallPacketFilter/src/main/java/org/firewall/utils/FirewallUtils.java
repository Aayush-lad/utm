package org.firewall.utils;

public class FirewallUtils {
    public static boolean validateRule(String rule) {
        return rule.startsWith("ALLOW") || rule.startsWith("BLOCK");
    }
}
