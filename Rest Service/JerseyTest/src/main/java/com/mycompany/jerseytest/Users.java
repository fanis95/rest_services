package com.mycompany.jerseytest;

/**
 *
 * @author Pipis
 */
public class Users {

    private String uId;
    private String password;

    public Users() {
    }

    public Users(String uId) {
        this.uId = uId;
    }

    public Users(String uId, String password) {
        this.uId = uId;
        this.password = password;
    }

    @Override
    public String toString() {
        return "uId=" + uId;
    }

}
