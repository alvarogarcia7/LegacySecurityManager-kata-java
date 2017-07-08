package user;

public class PasswordCandidate {
    private final String firstTry;
    private final String secondTry;

    public PasswordCandidate(String firstTry, String secondTry) {
        this.firstTry = firstTry;
        this.secondTry = secondTry;
    }

    public boolean isValid(UserNotifier userNotifier) {
        boolean result = true;
        if (!firstTry.equals(secondTry)) {
            userNotifier.passwordsDidNotMatch();
            result = false;
        }

        if (firstTry.length() < 8) {
            userNotifier.passwordDidNotComplyWithPolicy();
            result = false;
        }

        return result;
    }

    public String encrypt() {
        // Encrypt the password (just reverse it, should be secure)
        String encryptedPassword = new StringBuilder(firstTry).reverse().toString();
        return encryptedPassword;
    }
}
