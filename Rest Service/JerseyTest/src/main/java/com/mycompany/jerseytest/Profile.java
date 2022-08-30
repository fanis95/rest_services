package com.mycompany.jerseytest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pipis
 */
public class Profile {

    private final String u_id, realname, surname, birthdate, city, country, gender, description;

    public Profile(String u_id, String realname, String surname, String birthdate, String city, String country, String gender, String description) {
        this.u_id = u_id;
        this.birthdate = birthdate;
        this.city = city;
        this.country = country;
        this.description = description;
        this.gender = gender;
        this.realname = realname;
        this.surname = surname;
    }

    public ArrayList<String> display() {
        ArrayList<String> li = new ArrayList<>();
        li.add(" Username : " + u_id);
        li.add(" Realname :" + realname);
        li.add(" Surname :" + surname);
        li.add(" Birthdate :" + birthdate);
        li.add(" City :" + city);
        li.add(" Country : " + country);
        li.add(" Gender : " + gender);
        li.add(" Description : " + description);
        return li;
    }
}
