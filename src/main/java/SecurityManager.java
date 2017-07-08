
import infrastructure.console.RealReadConsole;
import user.CreatingUser;
import user.communication.UserInput;
import user.communication.UserNotifier;

public class SecurityManager {
    public static void createUser() {
        new CreatingUser(
                    new UserInput(new RealReadConsole())
                    , new UserNotifier(System.out::println))
                .invoke();
    }
}
