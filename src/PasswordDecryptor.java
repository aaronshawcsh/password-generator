public class PasswordDecryptor {
    //decrypt a single password
    public static String decryptPassword(String encryptedPassword) {
        int randomizerLength = Integer.parseInt(encryptedPassword.substring(encryptedPassword.length() - 1));
        int randomizer = Integer.parseInt(encryptedPassword.substring(encryptedPassword.length() - (randomizerLength + 1), encryptedPassword.length() - 1));
        String decryptedPassword = "";
        for(int i = 0; i < encryptedPassword.length() - (randomizerLength + 1); i++) {
            decryptedPassword += "" + ((char) (encryptedPassword.charAt(i) - randomizer));
        }
        return decryptedPassword;
    }
}
