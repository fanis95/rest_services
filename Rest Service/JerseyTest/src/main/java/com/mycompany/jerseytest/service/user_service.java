
package com.mycompany.jerseytest.service;

import com.mycompany.jerseytest.Post;
import com.mycompany.jerseytest.Profile;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 * Services , καλούνται από το αρχείο FirstClass.java
 * @author Pipis
 */

public class user_service {
    
    private ResultSet set;
    private Statement st;
    private boolean message = false;
    
    
    public user_service() {

    }
    
    //Σύνδεση στη βάση
    public Statement connectToDB() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RESTDB", "webuser", "123456");
        st = (Statement) con.createStatement();
        return st;
    }
    
    //Προσθήκη φίλου
    public boolean addFriend(String logged, String guest) {     
        boolean flag = false;    
        
        try {
            st = (Statement) connectToDB();
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql1 = "select u_id1,u_id2 from friends where ('" + logged + "' = u_id1 and '" + guest + "' = u_id2)  or ('" + logged + "' = u_id2 and '" + guest + "' = u_id1)";
        String sql2 = "insert into friends (u_id1,u_id2) values ('" + logged + "','" + guest + "')";
        
        try {
            set = st.executeQuery(sql1);
            if (set.next()) {
                String user1 = set.getString("u_id1");
                String user2 = set.getString("u_id2");
                //Ελέγχω αν η φιλια υπάρχει ήδη
                if ((user1.equals(guest) || user2.equals(guest))) { 
                    flag = true;
                }
            } //ή αν προσπαθεί να προσθέσει τον εαυτό του
            else if(logged.equals(guest)){
                 flag=true;
            }
            
            //Αν δεν υπάρχει την δημιουργεί
            if (!flag) {
                st.execute(sql2);
                message = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return message;
        }
    }
    
    //Εγγραφή χρήστη
    public boolean adduser(String id, String pass) {

        try {
            st = (Statement) connectToDB();
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "insert into users (u_id,password) values ('" + id + "','" + pass + "')";
        
        try {
            st.execute(sql);
            //Μόλις δημιουργηθεί ο χρήστης δημιουργείτε και το προφιλ του (άδειο)
            addProfile(id);
            message = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return message;
        }
    }
    
    //Δημιουργία προφίλ
    public void addProfile(String id) {
        String sql = "insert into profile (u_id) values('" + id + "')";
  
        try { 
            st = (Statement) connectToDB();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Επεξεργασία προφίλ επιλέγωντας το πεδίο που θέλει να αλλάξει
    public boolean editMyProfile(String id, String field, String content) {
        String sql = "select p_id from users,profile where users.u_id = '" + id + "' AND users.u_id = profile.u_id";
        
        try {
            st = (Statement) connectToDB();
            set = st.executeQuery(sql);
            if (set.next()) {

                switch (field) {
                    case "Realname":
                        sql = "update profile set realname = '" + content + "' where p_id = " + set.getInt("p_id") + " ";
                        break;
                    case "Surname":
                        sql = "update profile set surname = '" + content + "' where p_id = " + set.getInt("p_id") + " ";
                        break;
                    case "Birthdate":
                        sql = "update profile set birthdate = '" + content + "' where p_id = " + set.getInt("p_id") + " ";
                        break;
                    case "City":
                        sql = "update profile set city = '" + content + "' where p_id = " + set.getInt("p_id") + " ";
                        break;
                    case "Country":
                        sql = "update profile set country = '" + content + "' where p_id = " + set.getInt("p_id") + " ";
                        break;
                    case "Gender":
                        sql = "update profile set gender = '" + content + "' where p_id = " + set.getInt("p_id") + " ";
                        break;
                    case "Description":
                        sql = "update profile set description = '" + content + "' where p_id = " + set.getInt("p_id") + " ";
                        break;
                }
       
                st.executeUpdate(sql);
                message = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return message;
        }
    }
    
    //Αναζήτηση φίλων βάση id συνδεδεμένου χρήστη
    public List<String> searchMyFriends(String id) {
        ArrayList<String> l1 = new ArrayList();
        
        try {
            st = (Statement) connectToDB();

            String sql = "SELECT u_id1,u_id2 FROM friends WHERE '" + id + "' = u_id1 OR '" + id + "' = u_id2";

            set = st.executeQuery(sql);

            while (set.next()) {
                String user1 = set.getString("u_id1");
                String user2 = set.getString("u_id2");
                
                //Επειδή στη βάση θα υπάρχουν και αντίστροφες εγγραφές
                //ελέγχω αν με έχει κάνει add ο άλλος ή εγώ αυτόν 
                if (id.equals(user1)) {
                    l1.add(user2);
                } else if (id.equals(user2)) {
                    l1.add(user1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l1;
    }
    
    //Διαγραφή φίλου βάση id συνδεδεμένου χρήστη
    public boolean deletefromFriends(String me, String deleted) {
        
        try {
            st = (Statement) connectToDB();
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "delete from friends where (u_id1 = '" + me + "' and u_id2 = '" + deleted + "') or  (u_id1 = '" + deleted + "' and u_id2 = '" + me + "')";
        try {
            st.execute(sql);
            message = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return message;
        }

    }
    
    //Δημιουργία post (Ποιος κάνει το post και σε ποιανου το profil)
    public boolean addPost(String posters_id, String posted_id, String post) {

        try {
            st = (Statement) connectToDB();
            String sql1 = "select p_id from profile where u_id = '" + posted_id + "'";
            set = st.executeQuery(sql1);
            if (set.next()) {
                String sql2 = "insert into posts (p_id,u_id,description) values (" + set.getInt("p_id") + ",'" + posters_id + "','" + post + "')";
                st.execute(sql2);
                message = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return message;
        }

    }
    
    //Προβολή profil βάση id
    public List<String> showMyProfile(String id) {
        ArrayList<String> l1 = null;
        String sql = "select u_id,realname,surname,birthdate,city,country,gender,description from profile where u_id = '" + id + "' ";

        try {
            st = (Statement) connectToDB();
            set = st.executeQuery(sql);

            if (set.next()) {
                String u_id = set.getString("u_id");
                String realname = set.getString("realname");
                String surname = set.getString("surname");
                String birthdate = set.getString("birthdate");
                String city = set.getString("city");
                String country = set.getString("country");
                String gender = set.getString("gender");
                String description = set.getString("description");
                Profile profile = new Profile(u_id, realname, surname, birthdate, city, country, gender, description);

                l1 = profile.display(); //Δικιά μας toString()
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return l1;
        }

    }
    
    //Προβολή posts βάση id
    public List<String> showPost(String id) {

        int counter = 0;
        ArrayList<String> l1 = new ArrayList();
        try {
            st = (Statement) connectToDB();
            //Παίρνουμε όλα τα post από το πιο προσφατο εως το πιο παλιό 
            String sql = "select * from posts,profile where posts.p_id = profile.p_id and profile.u_id = '" + id + "' order by post_id desc";
            set = st.executeQuery(sql);
            //Με τον counter κρατάμε μόνο τα 10 πιο πρόσφατα σε μια λίστα
            while (set.next() && counter < 10) {
                String u_id = set.getString("u_id");
                String desc = set.getString("description");
                Post post = new Post(u_id, desc);
                l1.add(post.toString());
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return l1;
        }
    }
    
    //Διαγραφή post βάση id και περιεχομένου
    public boolean delPost(String id, String desc) {
        
        try {
            st = (Statement) connectToDB();
            String sql1 = "select p_id from profile where u_id = '" + id + "'";
            set = st.executeQuery(sql1);
            if (set.next()) {
                String sql = "delete from posts where p_id=" + set.getInt("p_id") + " AND description='" + desc + "'";
                st.execute(sql);
                message = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return message;
        }
    }
    
    //Τροποποίηση post βάση id και περιεχομένου
    public boolean editPost(String id, String desc, String content) {
        
        try {
            st = (Statement) connectToDB();
            String sql1 = "select p_id from profile where u_id = '" + id + "'";
            set = st.executeQuery(sql1);
            if (set.next()) {
                String sql = "update posts set description  = '" + content + "' where  p_id=" + set.getInt("p_id") + " AND description='" + desc + "'";    
                st.execute(sql);
                message = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return message;
        }
    }
    
    //Είσοδος στο social media με credentials
    public boolean login(String id, String pass) {

        try {
            st = (Statement) connectToDB();
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select u_id from users where u_id= '" + id + "' and password='" + pass + "'";
        try {
            set = st.executeQuery(sql);
            if (set.next()) {   //Αν γίνει επιτυχημένα η σύνδεση κρατάμε το id του χρήστη           
                if (set.getString("u_id").equals(id)) {
                    message = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return message;
        }
    }
    
    public Response getRes(Boolean message)
    {
        if (message) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
    
    public Response getRes2(List<String> l1){
        
        if (l1 == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            GenericEntity generic = new GenericEntity<List<String>>(l1) {
            };
            return Response.status(Response.Status.OK).entity(generic).build();
        }
    }
    
}
