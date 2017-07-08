package user;

public class PasswordCandidate {
    private final String firstTry;
    private final String secondTry;

    public PasswordCandidate(String firstTry, String secondTry) {
        this.firstTry = firstTry;
        this.secondTry = secondTry;
    }

    public boolean isValid() {
        return firstTry.equals(secondTry) && firstTry.length()>=8;
    }
}
