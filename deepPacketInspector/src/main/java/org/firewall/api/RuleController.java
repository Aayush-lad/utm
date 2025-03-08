package org.firewall.api;

import org.firewall.storage.FirewallRule;
import org.firewall.storage.FirewallRuleRepository;
import org.firewall.storage.MongoStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.*;
import org.firewall.core.FirewallManager;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/firewalldpi")
public class RuleController {

    @Autowired
    private FirewallManager firewallManager;

    @Autowired
    private MongoStorage mongoStorage; // ✅ Replace RedisStorage with MongoStorage
    @Autowired
    private FirewallRuleRepository ruleRepository;
    @PostMapping("/add")
    public List<FirewallRule> addRule(@RequestBody String rule) {
        FirewallRule firewallRule = new FirewallRule(rule);
        System.out.println(rule);
        firewallManager.addRule(rule);
        mongoStorage.addBlockedIP(rule);
        ruleRepository.save(firewallRule);
        return ruleRepository.findAll();
    }

    @DeleteMapping("/remove")
    public List<FirewallRule>  removeRule(@RequestParam String ruleId) {
        System.out.println(ruleId);
        firewallManager.removeRule(ruleId);  // ✅ Use instance method instead of static call
        mongoStorage.removeBlockedIP(ruleId); // ✅ Remove rule/IP from MongoDB
        ruleRepository.deleteById(ruleId);
        return ruleRepository.findAll();
    }

    @GetMapping("/rules")
    public List<FirewallRule> getRules() {
        List<FirewallRule> rules = ruleRepository.findAll();
        return rules;
    }
}
