import shellFunctions.Pathfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        List<String> builtins = builtins();
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            String[] str = input.split(" ");
            String command = str[0];
            String parameter = "";
            if (str.length > 2) {
                for (int i = 1; i < str.length; i++) {
                    if (i < str.length - 1) {
                        parameter += str[i] + " ";
                    } else {
                        parameter += str[i];
                    }
                }
            } else if (str.length > 1) {
                parameter = str[1];
            }
            switch (command) {
                case "exit":
                    if (parameter.equals("0")) {
                        System.exit(0);
                    } else {
                        System.out.println(input + ": command not found");
                    }
                    break;
                case "echo":
                    System.out.println(parameter);
                    break;
                case "type":
                    if (parameter.equals(builtins.get(0)) ||
                            parameter.equals(builtins.get(1)) ||
                            parameter.equals(builtins.get(2))) {
                        System.out.println(parameter + " is a shell builtin");
                    } else {
                            String path = getPath(parameter);
                        if (path != null) {
                            System.out.println(parameter + " is " + path);
                        } else {
                            System.out.println(parameter + ": not found");
                        }
                    }
                    break;
                default:
                    runCommand(command, parameter);
            }
        }
    }
    private static String getPath(String parameter) {
        for (String path : System.getenv("PATH").split(":")) {
            Path fullPath = Path.of(path, parameter);
            if (Files.isRegularFile(fullPath)) {
                return fullPath.toString();
            }
        }
        return null;
    }
    private static List<String> builtins() {
        List<String> builtins = new ArrayList<>();
        builtins.add("exit");
        builtins.add("echo");
        builtins.add("type");
        return builtins;
    }

    public static void runCommand(String command, String args){
        try {
            // Get the full path of the command
            String commandPath = getPath(command);
            String mainCommand = Path.of(commandPath).getFileName().toString();
            if (mainCommand == null) {
                System.out.println(command + ": command not found");
                return;
            }
            // Create a new process
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(mainCommand, args);

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            // Read the output of the command
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

