package org.firewall.service;

import org.firewall.model.FirewallRule;
import org.firewall.repository.FirewallRuleRepository;
import org.firewall.utils.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirewallService {

    @Autowired
    private FirewallRuleRepository repository;

    @Autowired
    private CommandExecutor commandExecutor;

    public List<FirewallRule> getAllRules() {
        return repository.findAll();
    }

    public FirewallRule addRule(FirewallRule rule) {
        String command;
        if ("DENY".equalsIgnoreCase(rule.getAction())) {
            command = String.format("sudo iptables -A INPUT -s %s -d %s -p tcp --dport %d -j DROP",
                    rule.getSourceIp(), rule.getDestinationIp(), rule.getPort());
        } else {
            command = String.format("sudo iptables -A INPUT -s %s -d %s -p tcp --dport %d -j ACCEPT",
                    rule.getSourceIp(), rule.getDestinationIp(), rule.getPort());
        }
        System.out.println("Executing " +command);
        try{
            // commandExecutor.executeCommand(command);
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        System.out.println("Success");
        return repository.save(rule);
    }

    public void deleteRule(String id) {
        Optional<FirewallRule> rule = repository.findById(id);
        rule.ifPresent(r -> {
            String command = String.format("sudo iptables -D INPUT -s %s -d %s -p tcp --dport %d -j %s",
                    r.getSourceIp(), r.getDestinationIp(), r.getPort(), r.getAction());
            // commandExecutor.executeCommand(command);
            repository.delete(r);
        });
    }
}
