public class PasswordEncryptor {
    //encrypt generated password in a Caesar cipher
    public static String encryptPassword(String passwordToEncrypt) {
        int randomizer = (int) (Math.random() * 10);
        int randomizerLength = Integer.toString(randomizer).length();
        String encryptedPassword = "";
        for(int i = 0; i < passwordToEncrypt.length(); i++) {
            encryptedPassword += "" + ((char) (passwordToEncrypt.charAt(i) + randomizer));
        }
        encryptedPassword += "" + randomizer + "" + randomizerLength;
        return encryptedPassword;
    }
}
