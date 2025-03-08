package org.firewall.storage;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blocked_ips")
public class BlockedIP {

    @Id
    private String id;
    private String ip;

    public BlockedIP(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }
}
