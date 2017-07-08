package acceptance;

import infrastructure.Console;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import user.CreatingUser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreatingUserShould {

    private Console console;
    private CreatingUser sut;

    @Before
    public void setUp() throws Exception {
        console = Mockito.mock(Console.class);
        sut = new CreatingUser() {
            @Override
            protected String readLine() throws IOException {
                return console.readLine();
            }

            @Override
            protected void printLine(String line) {
                console.printLine(line);
            }
        };
    }

    @Test
    public void create_a_user_given_all_conditions_are_met() {
        when(console.readLine()).thenReturn("root", "Root User", "12345678", "12345678");

        sut.invoke();

        verifyThat(console, printsLines(
                "Enter a username",
                "Enter your full name",
                "Enter your password",
                "Re-enter your password",
                String.format(
                        "Saving Details for User (%s, %s, %s)\n",
                        "root",
                        "Root User",
                        "87654321")));
    }

    @Test
    public void not_create_a_user_when_passwords_do_not_match() {
        when(console.readLine()).thenReturn("root", "Root User", "12345678", "123456789");

        sut.invoke();

        verifyThat(console, printsLines(
                "Enter a username",
                "Enter your full name",
                "Enter your password",
                "Re-enter your password",
                "The passwords don't match"));
    }

    private void verifyThat(Console console, List<String> lines) {
        lines.forEach(line -> verify(console).printLine(line));
    }

    private List<String> printsLines(String... lines) {
        return Arrays.asList(lines);
    }

}
