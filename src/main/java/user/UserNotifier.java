package user;

import infrastructure.WriteConsole;

public class UserNotifier {
    private WriteConsole console;

    public UserNotifier(WriteConsole console) {
        this.console = console;
    }

    public void userCreated(UserData userDataRequest) {
        console.printLine(String.format(
                "Saving Details for User (%s, %s, %s)\n",
                userDataRequest.username(),
                userDataRequest.fullName(),
                userDataRequest.passwordRepresentation().value()));
    }

    public void inform(String message) {
        console.printLine(message);
    }

    String prompt(String question, UserInput inputReadConsole) {
        inform(question);
        try {
            return inputReadConsole.readLine();
        } catch (UserInput.CannotReadLineException e) {
            error("Could not read the line");
            throw new RuntimeException("The program cannot continue");
        }
    }

    private void error(String message) {
        console.printLine(message);
    }
}
