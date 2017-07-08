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

        UserData userData = null;
        try {
            userNotifier.inform("Enter a username");
            String username = inputReadConsole.readLine();
            userNotifier.inform("Enter your full name");
            String fullName = inputReadConsole.readLine();
            userNotifier.inform("Enter your password");
            String password = inputReadConsole.readLine();
            userNotifier.inform("Re-enter your password");
            String confirmPassword = inputReadConsole.readLine();
            userData = new UserData(username, fullName, new PasswordCandidate(password, confirmPassword));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!userData.isValidPassword(userNotifier)) return;

        userNotifier.userCreated(userData);
    }

}
