package user;

public class CreatingUser {

    private UserInput userInput;
    private final UserNotifier userNotifier;

    public CreatingUser(UserInput userInput, UserNotifier userNotifier) {
        this.userInput = userInput;
        this.userNotifier = userNotifier;
    }

    public void invoke() {
        UserData userData = readUserData();
        if (!userData.isValidPassword(userNotifier)) {
            return;
        }
        userNotifier.userCreated(userData);
    }

    private UserData readUserData() {
        String username = userNotifier.prompt("Enter a username", userInput);
        String fullName = userNotifier.prompt("Enter your full name", userInput);
        String password = userNotifier.prompt("Enter your password", userInput);
        String confirmPassword = userNotifier.prompt("Re-enter your password", userInput);
        return new UserData(username, fullName, new PasswordCandidate(password, confirmPassword));
    }

}
