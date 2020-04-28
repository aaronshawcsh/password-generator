import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PasswordRetriever {
    //retrieve passwords from Caesar cipher
    public static void retrievePasswords() {
        String fileName = "passwordFile.txt";
        File passwordFile = new File("D:\\" + fileName);
        File decryptedPasswordFile = new File("D:\\" + "decrypted" + fileName);
        try {
            Scanner encryptedPasswordReader = new Scanner(passwordFile);
            while(encryptedPasswordReader.hasNextLine()) {
                String encryptedPassword = encryptedPasswordReader.nextLine();
                String decryptedPassword = PasswordDecryptor.decryptPassword(encryptedPassword);
                System.out.println(decryptedPassword);
            }
            encryptedPasswordReader.close();
        } catch(IOException e) {
            System.out.println("ERROR: Failed to retrieve passwords.");
        }
    }
}
