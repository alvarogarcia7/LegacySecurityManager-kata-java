package user;

import infrastructure.WriteConsole;

public class UserNotifier {
    private WriteConsole console;

    public UserNotifier(WriteConsole console) {
        this.console = console;
    }

    public void userCreated(UserData userData) {
        console.printLine(String.format(
                "Saving Details for User (%s, %s, %s)\n",
                userData.username(),
                userData.fullName(),
                userData.encryptedPassword()));
    }

    void passwordsDidNotMatch() {
        console.printLine("The passwords don't match");
    }

    void passwordDidNotComplyWithPolicy() {
        console.printLine("Password must be at least 8 characters in length");
    }

    public void inform(String message) {
        console.printLine(message);
    }
}
