package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreatingUser {
    public void invoke() {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String username = null;
        String fullName = null;
        String password = null;
        String confirmPassword = null;
        try {
            printLine("Enter a username");
            username = buffer.readLine();
            printLine("Enter your full name");
            fullName = buffer.readLine();
            printLine("Enter your password");
            password = buffer.readLine();
            printLine("Re-enter your password");
            confirmPassword = buffer.readLine();
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

    private void printLine(String x) {
        System.out.println(x);
    }
}
