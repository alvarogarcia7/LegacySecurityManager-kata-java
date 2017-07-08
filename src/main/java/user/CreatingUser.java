package user;

import infrastructure.ReadConsole;
import infrastructure.WriteConsole;

import java.io.IOException;

public class CreatingUser {

    private ReadConsole inputReadConsole;
    private final UserNotifier userNotifier;

    public CreatingUser(ReadConsole inputReadConsole, WriteConsole outputWriteConsole) {
        this.inputReadConsole = inputReadConsole;
        userNotifier = new UserNotifier(outputWriteConsole);
    }

    public void invoke() {
        UserData userData = readUserData();
        if (!userData.isValidPassword(userNotifier)) return;
        userNotifier.userCreated(userData);
    }

    private UserData readUserData() {
        UserData userData = null;
        try {
            String username = userNotifier.prompt("Enter a username", inputReadConsole);
            String fullName = userNotifier.prompt("Enter your full name", inputReadConsole);
            String password = userNotifier.prompt("Enter your password", inputReadConsole);
            String confirmPassword = userNotifier.prompt("Re-enter your password", inputReadConsole);
            userData = new UserData(username, fullName, new PasswordCandidate(password, confirmPassword));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData;
    }

}
