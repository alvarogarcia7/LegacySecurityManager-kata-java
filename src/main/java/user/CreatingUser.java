package user;

import infrastructure.Console;

import java.io.BufferedReader;
import java.io.IOException;

public class CreatingUser {

    private BufferedReader buffer;
    private Console inputConsole;

    public CreatingUser(Console inputConsole) {
        this.inputConsole = inputConsole;
    }

    public void invoke() {

        String username = null;
        String fullName = null;
        String password = null;
        String confirmPassword = null;
        try {
            printLine("Enter a username");
            username = inputConsole.readLine();
            printLine("Enter your full name");
            fullName = inputConsole.readLine();
            printLine("Enter your password");
            password = inputConsole.readLine();
            printLine("Re-enter your password");
            confirmPassword = inputConsole.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!password.equals(confirmPassword)) {
            printLine("The passwords don't match");
            return;
        }

        if (password.length() < 8) {
            printLine("Password must be at least 8 characters in length");
            return;
        }

        // Encrypt the password (just reverse it, should be secure)
        String encryptedPassword = new StringBuilder(password).reverse().toString();

        printLine(String.format(
                "Saving Details for User (%s, %s, %s)\n",
                username,
                fullName,
                encryptedPassword));
    }

    protected void printLine(String line) {
        System.out.println(line);
    }
}
