package com.mycompany.jerseytest;

/**
 *
 * @author Pipis
 */
public class Post {

    private String description;
    private String uId;

    public Post(String u_Id, String description) {
        this.uId = u_Id;
        this.description = description;
    }

    public Post() {
    }

    @Override
    public String toString() {
        return "User :" + uId + " posted : " + description;
    }
}
