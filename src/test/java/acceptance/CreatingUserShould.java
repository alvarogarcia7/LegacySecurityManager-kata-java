package acceptance;

import infrastructure.WriteConsole;
import infrastructure.ReadConsole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import user.CreatingUser;
import user.UserNotifier;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CreatingUserShould {

    private CreatingUser sut;
    private ReadConsole inputReadConsole;
    private WriteConsole outputWriteConsole;

    @Before
    public void setUp() throws Exception {
        inputReadConsole = Mockito.mock(ReadConsole.class);
        outputWriteConsole = Mockito.mock(WriteConsole.class);
        sut = new CreatingUser(inputReadConsole, new UserNotifier(outputWriteConsole));
    }

    @Test
    public void create_a_user_given_all_conditions_are_met() throws IOException {
        when(inputReadConsole.readLine()).thenReturn("root", "Root User", "12345678", "12345678");

        sut.invoke();

        verifyThat(outputWriteConsole, printsLines(
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
        when(inputReadConsole.readLine()).thenReturn("root", "Root User", "12345678", "123456789");

        sut.invoke();

        verifyThat(outputWriteConsole, printsLines(
                "Enter a username",
                "Enter your full name",
                "Enter your password",
                "Re-enter your password",
                "The passwords don't match"));
    }

    @Test
    public void not_create_a_user_when_password_does_not_comply_with_the_password_policy() throws IOException {
        when(inputReadConsole.readLine()).thenReturn("root", "Root User", "1234", "1234");

        sut.invoke();

        verifyThat(outputWriteConsole, printsLines(
                "Enter a username",
                "Enter your full name",
                "Enter your password",
                "Re-enter your password",
                "Password must be at least 8 characters in length"));
    }

    private void verifyThat(WriteConsole outputReadConsole, List<String> lines) {
        lines.forEach(line -> verify(outputReadConsole).printLine(line));
        verifyNoMoreInteractions(outputReadConsole);
    }

    private List<String> printsLines(String... lines) {
        return Arrays.asList(lines);
    }

}
