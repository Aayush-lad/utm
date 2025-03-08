package org.firewall.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirewallRuleRepository extends MongoRepository<FirewallRule, String> {
}
