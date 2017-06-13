package HomeWork11.Entity;

public class LogInUser {

    String email;
    String password;

    @Override
    public String toString() {
        return "LogInUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LogInUser(String email, String password) {

        this.email = email;
        this.password = password;
    }
}
