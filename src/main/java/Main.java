import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage

        for(int i=0;i<10;i++){
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit 0")){
                return;
            }
            else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
