package user;

import infrastructure.WriteConsole;
import infrastructure.ReadConsole;

import java.io.IOException;

public class CreatingUser {

    private ReadConsole inputReadConsole;
    private WriteConsole outputWriteConsole;

    public CreatingUser(ReadConsole inputReadConsole, WriteConsole outputWriteConsole) {
        this.inputReadConsole = inputReadConsole;
        this.outputWriteConsole = outputWriteConsole;
    }

    public void invoke() {

        String username = null;
        String fullName = null;
        String password = null;
        String confirmPassword = null;
        UserData userData = null;
        try {
            outputWriteConsole.printLine("Enter a username");
            username = inputReadConsole.readLine();
            outputWriteConsole.printLine("Enter your full name");
            fullName = inputReadConsole.readLine();
            outputWriteConsole.printLine("Enter your password");
            password = inputReadConsole.readLine();
            outputWriteConsole.printLine("Re-enter your password");
            confirmPassword = inputReadConsole.readLine();
            userData = new UserData(username, fullName, new PasswordCandidate(password, confirmPassword));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!compliesWithPasswordPolicy(password, confirmPassword, userData)) return;

        // Encrypt the password (just reverse it, should be secure)
        String encryptedPassword = new StringBuilder(password).reverse().toString();

        outputWriteConsole.printLine(String.format(
                "Saving Details for User (%s, %s, %s)\n",
                userData.username(),
                userData.fullName(),
                encryptedPassword));
    }

    private boolean compliesWithPasswordPolicy(String password, String confirmPassword, UserData userData) {
        boolean result = true;
        if (!password.equals(confirmPassword)) {
            outputWriteConsole.printLine("The passwords don't match");
            result = false;
        }

        if (password.length() < 8) {
            outputWriteConsole.printLine("Password must be at least 8 characters in length");
            result = false;
        }
        assert result == userData.isValidPassword();
        return result;
    }

}
