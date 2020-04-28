import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class PasswordWriter {
    //write encrypted password to file
    public static boolean writePassword(String encryptedPassword) {
        String fileName = "passwordFile.txt";
        File passwordFile = new File("D:\\" + fileName);
        try {
            PrintWriter encryptedPasswordWriter = new PrintWriter(passwordFile);
            encryptedPasswordWriter.write(encryptedPassword);
            encryptedPasswordWriter.close();
            return true;
        } catch(IOException e) {
            return false;
        }
    }
}
