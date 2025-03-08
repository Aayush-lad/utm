package org.firewall.storage;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "firewall_rules") // ✅ MongoDB collection
public class FirewallRule {

    @Id
    private String id;
    private String rule;

    public FirewallRule() {}

    public FirewallRule(String rule) {
        this.rule = rule;
    }

    public String getId() {
        return id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
