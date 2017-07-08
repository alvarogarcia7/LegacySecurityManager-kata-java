package user;

import infrastructure.ReadConsole;
import infrastructure.RealReadConsole;

import java.io.IOException;

public class UserInput  {
    private ReadConsole readConsole;

    public UserInput(ReadConsole readConsole) {
        this.readConsole = readConsole;
    }

    public String readLine() throws CannotReadLineException {
        try {
            return readConsole.readLine();
        } catch (IOException e) {
            throw new CannotReadLineException(e);
        }
    }

    public class CannotReadLineException extends Throwable {
        public CannotReadLineException(Throwable e) {
            super(e);
        }
    }
}
