package infrastructure.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RealReadConsole implements ReadConsole {
    public final BufferedReader buffer;

    {
        buffer = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public String readLine() throws IOException {
        return buffer.readLine();
    }
}
