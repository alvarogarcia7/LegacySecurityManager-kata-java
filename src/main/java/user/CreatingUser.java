package user;

import infrastructure.Console;

import java.io.IOException;

public class CreatingUser {

    private Console inputConsole;
    private Console outputConsole;

    public CreatingUser(Console inputConsole, Console outputConsole) {
        this.inputConsole = inputConsole;
        this.outputConsole = outputConsole;
    }

    public void invoke() {

        String username = null;
        String fullName = null;
        String password = null;
        String confirmPassword = null;
        try {
            outputConsole.printLine("Enter a username");
            username = inputConsole.readLine();
            outputConsole.printLine("Enter your full name");
            fullName = inputConsole.readLine();
            outputConsole.printLine("Enter your password");
            password = inputConsole.readLine();
            outputConsole.printLine("Re-enter your password");
            confirmPassword = inputConsole.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!password.equals(confirmPassword)) {
            outputConsole.printLine("The passwords don't match");
            return;
        }

        if (password.length() < 8) {
            outputConsole.printLine("Password must be at least 8 characters in length");
            return;
        }

        // Encrypt the password (just reverse it, should be secure)
        String encryptedPassword = new StringBuilder(password).reverse().toString();

        outputConsole.printLine(String.format(
                "Saving Details for User (%s, %s, %s)\n",
                username,
                fullName,
                encryptedPassword));
    }

}
