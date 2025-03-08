package org.firewall.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MongoStorage {

    @Autowired
    private BlockedIPRepository blockedIPRepository;

    public void addBlockedIP(String ip) {
        blockedIPRepository.save(new BlockedIP(ip));
        System.out.println("🛑 Blocked IP stored in MongoDB: " + ip);
    }

    public void removeBlockedIP(String ip) {
        blockedIPRepository.deleteByIp(ip);
        System.out.println("✅ Blocked IP removed from MongoDB: " + ip);
    }

    public List<BlockedIP> getBlockedIPs() {
        return blockedIPRepository.findAll();
    }
}
