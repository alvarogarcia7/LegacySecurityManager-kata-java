package acceptance;

import infrastructure.Console;
import org.junit.Test;
import org.mockito.Mockito;
import user.CreatingUser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreatingUserShould {

    @Test
    public void create_a_user_given_all_conditions_are_met() {
        Console console = Mockito.mock(Console.class);
        when(console.readLine()).thenReturn("root", "Root User", "12345678", "12345678");
        CreatingUser sut = new CreatingUser() {
            @Override
            protected String readLine() throws IOException {
                return console.readLine();
            }

            @Override
            protected void printLine(String x) {
                console.printLine(x);
            }
        };

        sut.invoke();

        verifyThat(console, printsLines(
                printLine("Enter a username"),
                printLine("Enter your full name"),
                printLine("Enter your password"),
                printLine("Re-enter your password"),
                printLine(String.format(
                        "Saving Details for User (%s, %s, %s)\n",
                        "root",
                        "Root User",
                        "87654321"))));
    }

    private void verifyThat(Console console, List<String> lines) {
        lines.forEach(line -> verify(console).printLine(line));
    }

    private List<String> printsLines(String... lines) {
        return Arrays.asList(lines);
    }

    private String printLine(String line) {
        return line;
    }
}
