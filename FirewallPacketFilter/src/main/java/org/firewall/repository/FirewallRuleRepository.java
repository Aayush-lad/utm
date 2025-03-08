package org.firewall.repository;

import org.firewall.model.FirewallRule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirewallRuleRepository extends MongoRepository<FirewallRule, String> {
}
