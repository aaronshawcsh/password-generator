import java.io.*;
import java.util.Scanner;

public class PasswordRetriever {
    //retrieve passwords from Caesar cipher
    public static void retrievePasswords() {
        String fileName = "passwordFile.txt";
        final String ROOT_DRIVE_LETTER = "C";
        File passwordFile = new File(ROOT_DRIVE_LETTER + ":\\" + fileName);
        try {
            System.out.println("\nHere are your passwords:\nPlatform: Password");
            BufferedReader encryptedPasswordReader = new BufferedReader(new InputStreamReader(new FileInputStream(passwordFile)));
            while(true) {
                String encryptedPassword = encryptedPasswordReader.readLine();
                if(encryptedPassword == null || encryptedPassword.equals("")) break;
                String decryptedPassword = PasswordDecryptor.decryptPassword(encryptedPassword);
                System.out.println(decryptedPassword);
            }
            System.out.println();
            encryptedPasswordReader.close();
        } catch(IOException e) {
            System.out.println("\nERROR: Failed to retrieve passwords.\n");
        }
    }
}
