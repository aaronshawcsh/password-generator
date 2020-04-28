public class PasswordGenerator {
    //create password for specified platform
    //change to parameters coming soon: String platform, int minLength, int maxLength, char[][] validCharacterSets, boolean[] characterSetUsed
    public static boolean generatePassword(String platform, int minLength, int maxLength, char[] validCharacterSets) {
        String generatedPassword = "";
        final int SAFE_MINIMUM_PASSWORD_LENGTH = 9;
        if(minLength < SAFE_MINIMUM_PASSWORD_LENGTH) minLength = SAFE_MINIMUM_PASSWORD_LENGTH;
        if(maxLength < minLength) maxLength = minLength + 1;
        int passLength = (int) ((Math.random() * (maxLength - minLength)) + minLength);
        for(int i = 0; i < passLength; i++) {
            generatedPassword += "" + getRandomValidCharacter(validCharacterSets);
        }
        String encryptedPassword = PasswordEncryptor.encryptPassword(platform + ": " + generatedPassword);
        if(!encryptedPassword.contains("\\") && PasswordWriter.writePassword(encryptedPassword)) return true;
        return false;
    }

    //currently working on rulesets for characters, this function will be massively changed
    //proposed parameters: char[] validCharacterSet, boolean[] characterSetUsed
    private static char getRandomValidCharacter(char[] validCharacterSet) {
        int[] randomIndices = new int[validCharacterSet.length];
        for(int i = 0; i < randomIndices.length; i++) randomIndices[i] = (int) (Math.random() * validCharacterSet.length);
        return validCharacterSet[randomIndices[(int) (Math.random() * randomIndices.length)]];
    }
}
