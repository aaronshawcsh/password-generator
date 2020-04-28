import java.io.*;
import javax.swing.*;
import java.util.Scanner;

public class PasswordGenerator extends JFrame {
    public static void main(String[] args) {
        //testing section
        /**/

        /**/

        //running section
        runInterface();
        /*
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
                if(generatePassword(platform, minLength, maxLength, generateValidCharacterSet())) System.out.println("Password successfully generated.");
                else System.out.println("ERROR: Password generation failed.");
            } else if (choice == 2) {
                retrievePasswords();
            } else if(choice == 3) {
                System.out.println("Thank you for using the password generator.");
                break;
            } else {
                System.out.println("Please choose between the menu option numbers.");
            }
            prompt();
        }
        */
    }

    private static void runInterface() {
        
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

    //--------------------------------------------------------------------------------//

    //PASSWORD GENERATION
    //create password for specified platform
    //change to parameters coming soon: String platform, int minLength, int maxLength, char[][] validCharacterSets, boolean[] characterSetUsed
    private static boolean generatePassword(String platform, int minLength, int maxLength, char[] validCharacterSets) {
        String generatedPassword = "";
        final int SAFE_MINIMUM_PASSWORD_LENGTH = 9;
        if(minLength < SAFE_MINIMUM_PASSWORD_LENGTH) minLength = SAFE_MINIMUM_PASSWORD_LENGTH;
        if(maxLength < minLength) maxLength = minLength + 1;
        int passLength = (int) ((Math.random() * (maxLength - minLength)) + minLength);
        for(int i = 0; i < passLength; i++) {
            generatedPassword += "" + getRandomValidCharacter(validCharacterSets);
        }
        String encryptedPassword = encryptPassword(platform + ": " + generatedPassword);
        if(!encryptedPassword.contains("\\") && writePassword(encryptedPassword)) return true;
        return false;
    }

    //currently working on rulesets for characters, this function will be massively changed
    //proposed parameters: char[] validCharacterSet, boolean[] characterSetUsed
    private static char getRandomValidCharacter(char[] validCharacterSet) {
        int[] randomIndices = new int[validCharacterSet.length];
        for(int i = 0; i < randomIndices.length; i++) randomIndices[i] = (int) (Math.random() * validCharacterSet.length);
        return validCharacterSet[randomIndices[(int) (Math.random() * randomIndices.length)]];
    }
    //END OF PASSWORD GENERATION

    //--------------------------------------------------------------------------------//

    //PASSWORD ENCRYPTION
    //encrypt generated password in a Caesar cipher
    private static String encryptPassword(String passwordToEncrypt) {
        int randomizer = (int) (Math.random() * 10);
        int randomizerLength = Integer.toString(randomizer).length();
        String encryptedPassword = "";
        for(int i = 0; i < passwordToEncrypt.length(); i++) {
            encryptedPassword += getSafeString(passwordToEncrypt.charAt(i), randomizer);
        }
        encryptedPassword += "" + randomizer + "" + randomizerLength;
        return encryptedPassword;
    }

    private static String getSafeString(char nextChar, int randomizer) {
        char randomizedCharacter = (char)(nextChar + randomizer);
        String safeString = "" + randomizedCharacter;
        if(randomizedCharacter == '\'' || randomizedCharacter == '\\' || randomizedCharacter == '\"') {
            safeString = "\\" + randomizedCharacter;
        }
        return safeString;
    }
    //END OF PASSWORD ENCRYPTION

    //--------------------------------------------------------------------------------//

    //PASSWORD WRITING
    final static String ROOT_DRIVE_LETTER = "C";
    //write encrypted password to file
    private static boolean writePassword(String encryptedPassword) {
        String fileName = "passwordFile.txt";
        File passwordFile = new File(ROOT_DRIVE_LETTER + ":\\" + fileName);
        try (FileWriter fileWriter = new FileWriter(passwordFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter encryptedPasswordWriter = new PrintWriter(bufferedWriter)) {

            encryptedPasswordWriter.println(encryptedPassword);
            encryptedPasswordWriter.close();
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //END OF PASSWORD WRITING

    //--------------------------------------------------------------------------------//

    //PASSWORD DECRYPTION
    //decrypt a single password
    private static String decryptPassword(String encryptedPassword) {
        int randomizerLength = Integer.parseInt(encryptedPassword.substring(encryptedPassword.length() - 1));
        int randomizer = Integer.parseInt(encryptedPassword.substring(encryptedPassword.length() - (randomizerLength + 1), encryptedPassword.length() - 1));
        String decryptedPassword = "";
        for(int i = 0; i < encryptedPassword.length() - (randomizerLength + 1); i++) {
            decryptedPassword += "" + ((char) (encryptedPassword.charAt(i) - randomizer));
        }
        return decryptedPassword;
    }
    //END OF PASSWORD DECRYPTION

    //--------------------------------------------------------------------------------//

    //PASSWORD RETRIEVAL
    //retrieve passwords from Caesar cipher
    private static void retrievePasswords() {
        String fileName = "passwordFile.txt";
        File passwordFile = new File(ROOT_DRIVE_LETTER + ":\\" + fileName);
        try {
            System.out.println("\nHere are your passwords:\nPlatform: Password");
            BufferedReader encryptedPasswordReader = new BufferedReader(new InputStreamReader(new FileInputStream(passwordFile)));
            while(true) {
                String encryptedPassword = encryptedPasswordReader.readLine();
                if(encryptedPassword == null || encryptedPassword.equals("")) break;
                String decryptedPassword = decryptPassword(encryptedPassword);
                System.out.println(decryptedPassword);
            }
            System.out.println();
            encryptedPasswordReader.close();
        } catch(IOException e) {
            System.out.println("\nERROR: Failed to retrieve passwords.\n");
        }
    }
    //END OF PASSWORD RETRIEVAL

    //--------------------------------------------------------------------------------//

    //UNIT TESTS
}
