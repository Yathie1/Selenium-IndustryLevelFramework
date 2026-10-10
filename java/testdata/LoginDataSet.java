package testdata;

public class LoginDataSet {

    private LoginData validUser;
    private LoginData lockedUser;
    private LoginData problemUser;

    public LoginData getValidUser() {
        return validUser;
    }

    public LoginData getLockedUser() {
        return lockedUser;
    }

    public LoginData getProblemUser() {
        return problemUser;
    }

    public void setValidUser(LoginData validUser) {
        this.validUser = validUser;
    }

    public void setLockedUser(LoginData lockedUser) {
        this.lockedUser = lockedUser;
    }

    public void setProblemUser(LoginData problemUser) {
        this.problemUser = problemUser;
    }
}