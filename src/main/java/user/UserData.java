package user;

public class UserData {
    private final String username;
    private final String fullName;
    private final PasswordCandidate passwordCandidate;

    public UserData(String username, String fullName, PasswordCandidate passwordCandidate) {
        this.username = username;
        this.fullName = fullName;
        this.passwordCandidate = passwordCandidate;
    }

    public boolean isValidPassword(UserNotifier userNotifier) {
        return passwordCandidate.isValid(userNotifier);
    }

    public String username() {
        return username;
    }

    public String fullName() {
        return fullName;
    }

    public String encryptedPassword() {
        return passwordCandidate.encrypt();
    }
}
