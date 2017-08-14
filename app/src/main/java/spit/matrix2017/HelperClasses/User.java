package spit.matrix2017.HelperClasses;

/**
 * Created by USER on 15-08-2017.
 */

public class User {
    String name,email,profile,UID,type;

    public User(String name, String email, String profile, String UID, String type) {
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.UID = UID;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile() {
        return profile;
    }

    public String getUID() {
        return UID;
    }

    public String getType() {
        return type;
    }
}
