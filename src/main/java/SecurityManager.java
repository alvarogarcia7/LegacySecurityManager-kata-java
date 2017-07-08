
import infrastructure.RealReadConsole;
import user.CreatingUser;
import user.UserInput;
import user.UserNotifier;

public class SecurityManager {
    public static void createUser() {
        new CreatingUser(new UserInput(new RealReadConsole()), new UserNotifier(System.out::println)).invoke();
    }
}
