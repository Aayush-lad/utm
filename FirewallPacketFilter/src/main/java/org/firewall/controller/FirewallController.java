package org.firewall.controller;

import org.firewall.model.FirewallRule;
import org.firewall.service.FirewallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/firewall")
public class FirewallController {

    @Autowired
    private FirewallService service;

    @GetMapping("/rules")
    public List<FirewallRule> getRules() {
        System.out.println("getting rules");
        return service.getAllRules();
    }
    @PostMapping("/rules")
    public ResponseEntity<List<FirewallRule>> addRule(@RequestBody FirewallRule rule) {
    System.out.print(rule);
    FirewallRule savedRule = service.addRule(rule);
    System.out.println("ADDING RULE: " + savedRule);
    return ResponseEntity.ok(service.getAllRules()); 
}

    @DeleteMapping("/rules/{id}")
    public ResponseEntity<List<FirewallRule>> deleteRule(@PathVariable String id) {
    service.deleteRule(id);
    System.out.println("DELETED RULE ID: " + id);
    return ResponseEntity.ok(service.getAllRules()); 
    }

}
