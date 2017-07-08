package user;

import io.vavr.control.Either;
import user.communication.UserInput;
import user.communication.UserNotifier;
import user.creation.UserData;
import user.creation.UserDataRequest;
import user.password.PasswordCandidate;

import java.util.List;

public class CreatingUser {

    private UserInput userInput;
    private final UserNotifier userNotifier;

    public CreatingUser(UserInput userInput, UserNotifier userNotifier) {
        this.userInput = userInput;
        this.userNotifier = userNotifier;
    }

    public void invoke() {
        UserDataRequest userDataRequest = readUserData();
        Either<List<String>, UserData> result = userDataRequest.validate();
        if (result.isLeft()) {
            result.left().forEach(messages -> messages.forEach(userNotifier::inform));
            return;
        }
        result.forEach(userNotifier::userCreated);
    }

    private UserDataRequest readUserData() {
        String username = userNotifier.prompt("Enter a username", userInput);
        String fullName = userNotifier.prompt("Enter your full name", userInput);
        String password = userNotifier.prompt("Enter your password", userInput);
        String confirmPassword = userNotifier.prompt("Re-enter your password", userInput);
        return new UserDataRequest(username, fullName, new PasswordCandidate(password, confirmPassword));
    }

}
