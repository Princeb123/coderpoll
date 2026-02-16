public class user {

    int user_id;
    String name;
    String email;
    String password;

    public user(int user_id, String name, String email, String password) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return name;
    }
}