package user;

import infrastructure.ReadConsole;
import infrastructure.WriteConsole;

import java.io.IOException;

public class CreatingUser {

    private ReadConsole inputReadConsole;
    private final UserNotifier userNotifier;
    private WriteConsole outputWriteConsole;

    public CreatingUser(ReadConsole inputReadConsole, WriteConsole outputWriteConsole) {
        this.inputReadConsole = inputReadConsole;
        this.outputWriteConsole = outputWriteConsole;
        userNotifier = new UserNotifier(outputWriteConsole);
    }

    public void invoke() {

        String password = null;
        String confirmPassword = null;
        UserData userData = null;
        try {
            outputWriteConsole.printLine("Enter a username");
            String username = inputReadConsole.readLine();
            outputWriteConsole.printLine("Enter your full name");
            String fullName = inputReadConsole.readLine();
            outputWriteConsole.printLine("Enter your password");
            password = inputReadConsole.readLine();
            outputWriteConsole.printLine("Re-enter your password");
            confirmPassword = inputReadConsole.readLine();
            userData = new UserData(username, fullName, new PasswordCandidate(password, confirmPassword));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!compliesWithPasswordPolicy(password, confirmPassword, userData)) return;

        userNotifier.userCreated(userData);
    }

    private boolean compliesWithPasswordPolicy(String password, String confirmPassword, UserData userData) {
        boolean result = true;
        if (!password.equals(confirmPassword)) {
            userNotifier.passwordsDidNotMatch();
            result = false;
        }

        if (password.length() < 8) {
            userNotifier.passwordDidNotComplyWithPolicy();
            result = false;
        }
        assert result == userData.isValidPassword();
        return result;
    }

}
