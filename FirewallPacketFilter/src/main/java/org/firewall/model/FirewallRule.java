package org.firewall.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "firewall_rules")
public class FirewallRule {
    @Id
    private String id;
    private String sourceIp;
    private String destinationIp;
    private int port;
    private String action; // ALLOW / DENY

    // Constructors
    public FirewallRule() {}

    public FirewallRule(String sourceIp, String destinationIp, int port, String action) {
        this.sourceIp = sourceIp;
        this.destinationIp = destinationIp;
        this.port = port;
        this.action = action;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSourceIp() { return sourceIp; }
    public void setSourceIp(String sourceIp) { this.sourceIp = sourceIp; }

    public String getDestinationIp() { return destinationIp; }
    public void setDestinationIp(String destinationIp) { this.destinationIp = destinationIp; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}
