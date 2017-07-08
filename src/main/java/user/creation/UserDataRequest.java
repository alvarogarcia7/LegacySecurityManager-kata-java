package user.creation;

import io.vavr.control.Either;
import user.password.PasswordCandidate;

import java.util.List;

public class UserDataRequest {
    private final String username;
    private final String fullName;
    private final PasswordCandidate passwordCandidate;

    public UserDataRequest(String username, String fullName, PasswordCandidate passwordCandidate) {
        this.username = username;
        this.fullName = fullName;
        this.passwordCandidate = passwordCandidate;
    }

    public Either<List<String>, UserData> validate() {
        return passwordCandidate.isValid()
                .map(validPassword -> new UserData(username, fullName, validPassword));
    }
}
