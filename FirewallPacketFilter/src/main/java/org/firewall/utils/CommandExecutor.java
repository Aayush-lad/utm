package org.firewall.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CommandExecutor {

    public void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
            process.waitFor();

            // Log command output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Command Output: " + line);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
