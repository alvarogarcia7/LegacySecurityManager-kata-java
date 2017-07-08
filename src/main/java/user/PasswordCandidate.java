package user;

import io.vavr.control.Either;
import io.vavr.control.Validation;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class PasswordCandidate {
    private final String firstTry;
    private final String secondTry;

    public PasswordCandidate(String firstTry, String secondTry) {
        this.firstTry = firstTry;
        this.secondTry = secondTry;
    }

    public Either<List<String>, EncryptedPassword> isValid() {
        return new PasswordValidator().validatePassword(firstTry, secondTry).toEither();
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

    class PasswordValidator {
        public Validation<List<String>, EncryptedPassword> validatePassword(String firstTry, String secondTry) {
            return Validation.narrow(validate(firstTry, secondTry)).bimap(Arrays::asList, Function.identity());
        }

        private Validation<String, EncryptedPassword> validate(String firstTry, String secondTry) {
            if (!firstTry.equals(secondTry)) {
                return Validation.invalid("The passwords don't match");
            }
            if (firstTry.length() < 8) {
                return Validation.invalid("Password must be at least 8 characters in length");
            }
            return Validation.valid(new EncryptedPassword(encrypt()));
        }

    }
}
