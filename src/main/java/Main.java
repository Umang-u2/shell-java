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
            if(command.equalsIgnoreCase("exit")){
                return;
            } else if(command.equalsIgnoreCase("echo")){
                System.out.println((firstSpaceIndex == -1)?"":input.substring(firstSpaceIndex+1));
            }
            else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
