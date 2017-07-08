
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecurityManager {
    public static void createUser() {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String username = null;
        String fullName = null;
        String password = null;
        String confirmPassword = null;
        try {
            System.out.println("Enter a username");
            username = buffer.readLine();
            System.out.println("Enter your full name");
            fullName = buffer.readLine();
            System.out.println("Enter your password");
            password = buffer.readLine();
            System.out.println("Re-enter your password");
            confirmPassword = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("The passwords don't match");
            return;
        }

        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters in length");
            return;
        }

        // Encrypt the password (just reverse it, should be secure)
        String encryptedPassword = new StringBuilder(password).reverse().toString();

        System.out.println(
            String.format(
            "Saving Details for User (%s, %s, %s)\n",
            username,
            fullName,
            encryptedPassword)
        );
    }
}
