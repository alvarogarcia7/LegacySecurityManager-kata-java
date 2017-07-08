package user.password;

import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;

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

    private String encrypt(String firstTry) {
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
            return Validation
                    .combine(validateSame(firstTry, secondTry), validateLength(firstTry))
                    .ap(this::buildPassword)
                    .bimap(Seq::asJava, Function.identity());
        }

        private Validation<String, String> validateLength(String password) {
            if (password.length() < 8) {
                return Validation.invalid("Password must be at least 8 characters in length");
            }
            return Validation.valid(password);
        }

        private Validation<String, String> validateSame(String firstTry, String secondTry) {
            if (!firstTry.equals(secondTry)) {
                return Validation.invalid("The passwords don't match");
            }
            return Validation.valid(firstTry);
        }

        private EncryptedPassword buildPassword(String s, String _) {
            return new EncryptedPassword(encrypt(s));
        }

    }
}
