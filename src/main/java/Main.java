import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage

        for(int i=0;i<10;i++){
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String command = input.split("\\s+")[0];
            int firstSpaceIndex = input.indexOf(" ");
            String arguments = (firstSpaceIndex == -1)?"":input.substring(firstSpaceIndex+1);
            List<String> acceptedCommands = getShellCommands();
            if(command.equalsIgnoreCase("exit")){
                return;
            } else if(command.equalsIgnoreCase("echo")){
                System.out.println(arguments);
            } else if(command.equalsIgnoreCase("type") && acceptedCommands.contains(arguments)){
                System.out.println(arguments+" is a shell builtin");
            }
            else {
                System.out.println(input + ": command not found");
            }
        }
    }

    private static List getShellCommands() {
        List<String> shellCommands = new ArrayList<String>();
        shellCommands.add("echo");
        shellCommands.add("exit");
        shellCommands.add("type");
        return shellCommands;
    }
}
