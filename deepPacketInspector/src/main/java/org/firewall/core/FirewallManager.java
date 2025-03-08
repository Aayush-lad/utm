package org.firewall.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Arrays;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.firewall.storage.FirewallRule;
import org.firewall.storage.FirewallRuleRepository;

@Component
public class FirewallManager {

    @Autowired
    private FirewallRuleRepository ruleRepository;
    public static void blockPacket(byte[] packetData) {
        String command = "iptables -A INPUT -s " + getSourceIP(packetData) + " -j DROP";
        executeCommand(command);
    }
    public List<FirewallRule> getRules() {
        return ruleRepository.findAll();
    }

    public static String addRule(String sourceIp) {
        if (sourceIp == null || sourceIp.isEmpty()) {
            return "⚠️ Invalid IP address. Rule not added.";
        }
        FirewallRule firewallRule = new FirewallRule();
        String command = "sudo iptables -A INPUT -s " + sourceIp + " -j DROP";

        executeCommand(command);
        return "Rule added: Blocking IP>>" + sourceIp;
    }

    public static String removeRule(String sourceIp) {
        if (sourceIp == null || sourceIp.isEmpty()) {
            return "⚠️ Invalid IP address. Rule not removed.";
        }

        String command = "sudo iptables -D INPUT -s " + sourceIp + " -j DROP";
        executeCommand(command);
        return "✅ Rule removed: Unblocked IP " + sourceIp;
    }


    private static String getSourceIP(byte[] packetData) {
        return extractSourceIP(packetData);
    }
    private static String extractSourceIP(byte[] packetData) {
        try {
            byte[] ipBytes = Arrays.copyOfRange(packetData, 12, 16);
            return InetAddress.getByAddress(ipBytes).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unknown IP";
        }
    }
    private static void executeCommand(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

