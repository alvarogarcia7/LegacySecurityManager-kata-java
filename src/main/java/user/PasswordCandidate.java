package user;

import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;

public class PasswordCandidate {
    private final String firstTry;
    private final String secondTry;

    public PasswordCandidate(String firstTry, String secondTry) {
        this.firstTry = firstTry;
        this.secondTry = secondTry;
    }

    public Either<List<String>, EncryptedPassword> isValid() {
        List<String> messages = new ArrayList<>();
        if (!firstTry.equals(secondTry)) {
            messages.add("The passwords don't match");
        }

        if (firstTry.length() < 8) {
            messages.add("Password must be at least 8 characters in length");
        }

        if(messages.isEmpty()){
            return Either.right(new EncryptedPassword(encrypt()));
        } else {
            return Either.left(messages);
        }
    }

    public String encrypt() {
        // Encrypt the password (just reverse it, should be secure)
        String encryptedPassword = new StringBuilder(firstTry).reverse().toString();
        return encryptedPassword;
    }

    public class EncryptedPassword {
        private final String value;

        public EncryptedPassword(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
