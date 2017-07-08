package user.creation;

import user.password.PasswordCandidate;

public class UserData {
    private final String username;
    private final String fullName;
    private final PasswordCandidate.EncryptedPassword password;

    public UserData(String username, String fullName, PasswordCandidate.EncryptedPassword password) {

        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    public PasswordCandidate.EncryptedPassword passwordRepresentation() {
        return password;
    }

    public String fullName() {
        return fullName;
    }

    public String username() {
        return username;
    }
}
