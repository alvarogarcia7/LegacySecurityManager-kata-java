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
        try {
            outputWriteConsole.printLine("Enter a username");
            username = inputReadConsole.readLine();
            outputWriteConsole.printLine("Enter your full name");
            fullName = inputReadConsole.readLine();
            outputWriteConsole.printLine("Enter your password");
            password = inputReadConsole.readLine();
            outputWriteConsole.printLine("Re-enter your password");
            confirmPassword = inputReadConsole.readLine();
            UserData userData = new UserData(username, fullName, new PasswordCandidate(password, confirmPassword));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (breaksThePasswordPolicy(password, confirmPassword)) return;

        // Encrypt the password (just reverse it, should be secure)
        String encryptedPassword = new StringBuilder(password).reverse().toString();

        outputWriteConsole.printLine(String.format(
                "Saving Details for User (%s, %s, %s)\n",
                username,
                fullName,
                encryptedPassword));
    }

    private boolean breaksThePasswordPolicy(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            outputWriteConsole.printLine("The passwords don't match");
            return true;
        }

        if (password.length() < 8) {
            outputWriteConsole.printLine("Password must be at least 8 characters in length");
            return true;
        }
        return false;
    }

}
