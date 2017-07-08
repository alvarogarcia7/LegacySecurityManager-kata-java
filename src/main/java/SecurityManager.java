
import infrastructure.RealReadConsole;
import user.CreatingUser;

public class SecurityManager {
    public static void createUser() {
        new CreatingUser(new RealReadConsole(), System.out::println).invoke();
    }
}
