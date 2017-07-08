package infrastructure;

import java.io.IOException;

public interface Console {
    String readLine() throws IOException;

    void printLine(String line);
}
