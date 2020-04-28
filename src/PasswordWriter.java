import java.io.*;

public class PasswordWriter {
    //write encrypted password to file
    public static boolean writePassword(String encryptedPassword) {
        String fileName = "passwordFile.txt";
        final String ROOT_DRIVE_LETTER = "C";
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
}
