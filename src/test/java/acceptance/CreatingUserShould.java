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
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CreatingUserShould {

    private Console inputConsole;
    private CreatingUser sut;
    private Console outputConsole;

    @Before
    public void setUp() throws Exception {
        inputConsole = Mockito.mock(Console.class);
        outputConsole = Mockito.mock(Console.class);
        sut = new CreatingUser(inputConsole, outputConsole) {
            protected void printLine(String line) {
                outputConsole.printLine(line);
            }
        };
    }

    @Test
    public void create_a_user_given_all_conditions_are_met() throws IOException {
        when(inputConsole.readLine()).thenReturn("root", "Root User", "12345678", "12345678");

        sut.invoke();

        verifyThat(outputConsole, printsLines(
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
    public void not_create_a_user_when_passwords_do_not_match() throws IOException {
        when(inputConsole.readLine()).thenReturn("root", "Root User", "12345678", "123456789");

        sut.invoke();

        verifyThat(outputConsole, printsLines(
                "Enter a username",
                "Enter your full name",
                "Enter your password",
                "Re-enter your password",
                "The passwords don't match"));
    }

    @Test
    public void not_create_a_user_when_password_does_not_comply_with_the_password_policy() throws IOException {
        when(inputConsole.readLine()).thenReturn("root", "Root User", "1234", "1234");

        sut.invoke();

        verifyThat(outputConsole, printsLines(
                "Enter a username",
                "Enter your full name",
                "Enter your password",
                "Re-enter your password",
                "Password must be at least 8 characters in length"));
    }

    private void verifyThat(Console outputConsole, List<String> lines) {
        lines.forEach(line -> verify(outputConsole).printLine(line));
        verifyNoMoreInteractions(outputConsole);
    }

    private List<String> printsLines(String... lines) {
        return Arrays.asList(lines);
    }

}
