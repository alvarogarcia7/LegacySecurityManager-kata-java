
import infrastructure.ReadConsole;
import infrastructure.WriteConsole;
import user.CreatingUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecurityManager {
    public static void createUser() {
        new CreatingUser(new ReadConsole() {
            public final BufferedReader buffer;

            {
                buffer = new BufferedReader(new InputStreamReader(System.in));

            }

            @Override
            public String readLine() throws IOException {
                return buffer.readLine();
            }
        }, System.out::println).invoke();
    }

}
