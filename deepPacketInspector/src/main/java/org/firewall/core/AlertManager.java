package org.firewall.core;

public class AlertManager {

    public static void sendAlert(String message) {
        System.out.println("ALERT: " + message);
        // Store alert or send email notification
    }
}
