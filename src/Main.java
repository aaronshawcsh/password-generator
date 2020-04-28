import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the password generator\n");
        prompt();
        while(sc.hasNextInt()) {
            int choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1) {
                System.out.print("Enter the name of the platform you would like a password for: ");
                String platform = sc.nextLine();
                System.out.print("Enter the minimum required length of the generated password (safe minimum is 9): ");
                int minLength = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter the maximum possible length of the generated password (check site descriptions): ");
                int maxLength = sc.nextInt();
                sc.nextLine();
                if(PasswordGenerator.generatePassword(platform, minLength, maxLength, generateValidCharacterSet())) System.out.println("Password successfully generated.");
                else System.out.println("ERROR: Password generation failed.");
            } else if (choice == 2) {
                PasswordRetriever.retrievePasswords();
            } else if(choice == 3) {
                System.out.println("Thank you for using the password generator.");
                break;
            } else {
                System.out.println("Please choose between the menu option numbers.");
            }
            prompt();
        }
    }

    private static void prompt() {
        System.out.println("Menu\n1) Generate a password\n2) Retrieve all passwords\n3) Quit\nPlease enter your choice (1-3): ");
    }

    //proposed modification - use Java Swing class and radiobuttons to create customized character sets, the character '\' is a known issue.
    private static char[] generateValidCharacterSet() {
        char[] specialCharacterSet = { '!', '@', '.', ',', '$', '#', '_', '-' };
        char[] validCharacterSet = new char[70];
        int indexMarker = 0;
        for(int i = 'A'; i < 'Z'; i++) {
            validCharacterSet[indexMarker++] = (char) i;
        }
        for(int i = 'a'; i < 'z'; i++) {
            validCharacterSet[indexMarker++] = (char) i;
        }
        for(int i = 48; i < 58; i++) {
            validCharacterSet[indexMarker++] = (char) i;
        }
        for(int i = 0; i < specialCharacterSet.length; i++) {
            validCharacterSet[indexMarker++] = specialCharacterSet[i];
        }
        return validCharacterSet;
    }
}
