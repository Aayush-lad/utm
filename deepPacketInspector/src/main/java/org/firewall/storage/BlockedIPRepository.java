package org.firewall.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlockedIPRepository extends MongoRepository<BlockedIP, String> {
    void deleteByIp(String ip);
}
