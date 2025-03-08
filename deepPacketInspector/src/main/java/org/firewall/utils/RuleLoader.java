package org.firewall.utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RuleLoader {
    private static final List<String> rules = new ArrayList<>();

    public static List<String> loadRules(String filePath) {
        try {
            List<String> ruleLines = Files.readAllLines(Paths.get(filePath));
            for (String rule : ruleLines) {
                if (!rule.trim().isEmpty() && !rule.startsWith("#")) {
                    rules.add(rule);
                }
            }
            System.out.println("Loaded " + rules.size() + " rules.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rules;
    }

}
