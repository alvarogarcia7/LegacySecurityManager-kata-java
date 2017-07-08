package user;

public class UserData {
    private final PasswordCandidate passwordCandidate;

    public UserData(String username, String fullName, PasswordCandidate passwordCandidate) {

        this.passwordCandidate = passwordCandidate;
    }

    public boolean isValidPassword() {
        return passwordCandidate.isValid();
    }
}
