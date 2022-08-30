package com.mycompany.clientrest;

import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;

/**
 *
 * @author Pipis
 */
public class Leitourgies {

    private Client client;
    private ClientResponse response;
    private String message;
    private List<String> result;

    public Leitourgies() { //Αρχικοποίηση Client's configuration 
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        client = Client.create(config);
    }

    //Ανάκτηση λίστας φίλων
    public List getFriends(String user) { //Ορισμός path που θα βρει την υλοποίηση + user ,για τον οποίο θα την τρέξει
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/searchmyfriends/" + user);
        ClientResponse res = target.type("application/json").get(ClientResponse.class); //Ορισμός τύπου του resource και μεθόδου του αιτήματος

        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
        } else { //Εκχώρηση του αποτελέσματος σε GenericType List πρίν την επιστροφή
            result = res.getEntity(new GenericType<List<String>>() {
            });
        }
        return result;
    }

    //Ανάκτηση profile συνδεδεμένου χρήστη
    public List getProfile(String user) {
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/showprofile/" + user);
        ClientResponse res = target.type("application/json").get(ClientResponse.class);

        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
        } else {
            result = res.getEntity(new GenericType<List<String>>() {
            });
        }
        return result;
    }

    //Ανάκτηση posts
    public List getPosts(String user) {
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/showpost/" + user);
        ClientResponse res = target.type("application/json").get(ClientResponse.class);

        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
        } else {
            result = res.getEntity(new GenericType<List<String>>() {
            });
        }
        return result;
    }

    //Εγγραφή νέου χρήστη , με μορφή input που του έχουμε ορίσει στην αντίστοιχη συνάρτηση στα services
    public String addUser(String id, String pass) {
        String input = id + "&" + pass;
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/add/" + input);
        response = target.type("application/json").put(ClientResponse.class);
        if (response.getStatus() != 200) {
            message = ("Something went wrong");
        } else {
            message = "User Added";
        }
        return message;
    }

    //Διαδικασία εισόδου στην εφαρμογή
    public Boolean login(String id, String pass) {
        Boolean result = false;
        String input = id + "&" + pass;
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/login/" + input);
        response = target.type("application/json").put(ClientResponse.class);

        if (response.getStatus() == 200) {
            result = true;
        }
        return result;
    }

    //Δημιουργία νέου post 
    public String addPost(String id, String target_id, String post) {
        String replace = post.replace(" ", "%20"); //Αντικαθιστούμε το κενό με %20 ,όπως στον asci για να μην έχουμε πρόβλημα 
        String input = id + "&" + target_id + "&" + replace; //κατά την αποστολή του αιτήματος στον εξυπηρετητή
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/addpost/" + input);
        ClientResponse res = target.type("application/json").get(ClientResponse.class);

        if (res.getStatus() != 200) {
            message = "Something went wrong";
        } else {
            message = "Post created";
        }
        return message;
    }

    //Προσθήκη νέου φίλου
    public String addFriend(String id, String friend) {
        String input = id + "&" + friend;
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/addfriend/" + input);
        ClientResponse res = target.type("application/json").put(ClientResponse.class);

        if (res.getStatus() != 200) { //Εμφάνιση μυνήματος αναλόγως την περίπτωση αποτυχίας ή επιτυχίας
            if (id.equals(friend)) {
                message = "You can't add yourself as friend!";
            } else {
                message = "User does not exist or is already friend";
            }
        } else {
            message = "Friend Added";
        }
        return message;
    }

    //Επεξεργασία πληροφοριών του profile 
    public String editProfile(String id, String field, String content) {
        String input = id + "&" + field + "&" + content;
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/editmyprofile/" + input);
        ClientResponse res = target.type("application/json").put(ClientResponse.class);

        if (res.getStatus() != 200) {
            message = "Something went wrong";
        } else {
            message = "Content updated";
        }
        return message;
    }

    //Διαγραφή φίλου 
    public String deleteFriend(String id, String friend) {
        String input = id + "&" + friend;
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/deletefriend/" + input);
        ClientResponse res = target.type("application/json").put(ClientResponse.class);

        if (res.getStatus() != 200) {
            message = "Something went wrong";
        } else {
            message = "Friend Deleted";
        }
        return message;
    }

    //Διαγραφή post
    public String deletePost(String id, String post) {
        String desc = post.substring(post.indexOf("posted : ") + 9);
        String replace = desc.replace(" ", "%20");
        String input = id + "&" + replace;
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/deletepost/" + input);
        ClientResponse res = target.type("application/json").put(ClientResponse.class);

        if (res.getStatus() != 200) {
            message = "Something went wrong";
        } else {
            message = "Post Deleted";
        }
        return message;
    }

    //Επεξεργασία ήδη υπάρχον post από το profile
    public String editPost(String id, String desc, String content) {
        desc = desc.substring(desc.indexOf("posted : ") + 9);
        String replace = desc.replace(" ", "%20"); //Αντικατάσταση κενού για desc , content
        String replace2 = content.replace(" ", "%20");
        String input = id + "&" + replace + "&" + replace2;
        WebResource target = client.resource("http://localhost:8080/JerseyTest/webresources/user/editpost/" + input);
        ClientResponse res = target.type("application/json").put(ClientResponse.class);

        if (res.getStatus() != 200) {
            message = "Something went wrong";
        } else {
            message = "Post Edited";
        }
        return message;
    }

}
