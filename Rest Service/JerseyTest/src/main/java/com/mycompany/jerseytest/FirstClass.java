package com.mycompany.jerseytest;

import com.mycompany.jerseytest.service.user_service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Pipis
 */
//Καθορισμός path και responses,καθώς και mapping.
//Χρήση annotations για χρήση/παραγωγή json,μέθοδος request
@Path("user/")
@Api(value = "/", description = "Operations")
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK"),
    @ApiResponse(code = 403, message = "Permission denied"),
    @ApiResponse(code = 500, message = "Something went wrong"),})
@RequestMapping(method = RequestMethod.GET, value = "/")
public class FirstClass {

    private user_service user;
    private boolean message;

    public FirstClass() {
        user = new user_service();
    }

    //Αναζήτηση φίλων
    @GET
    @Path("/searchmyfriends/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response searchFriends(@PathParam("id") String id) {
        List<String> l1;
        l1 = user.searchMyFriends(id);
        return user.getRes2(l1);
    }

    //Προβολή posts
    @GET
    @Path("/showpost/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response showPost(@PathParam("id") String id) {
        List<String> l1 = new ArrayList<>();
        l1.addAll(user.showPost(id));
        return user.getRes2(l1);
    }

    //Προβολή προφίλ
    @GET
    @Path("/showprofile/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response giveProfile(@PathParam("id") String id) {
        List<String> l1 = new ArrayList();
        l1 = user.showMyProfile(id);
        return user.getRes2(l1);
    }
    
    //Προσθήκη φίλου
    @PUT //logged -> συνδεδεμένος χρήστης ,guest-> αυτόν που προσθέτει
    @Path("/addfriend/{logged}&{guest}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFriend(@PathParam("logged") String logged, @PathParam("guest") String guest) {
        message = user.addFriend(logged, guest);
        return user.getRes(message);
    }
    
    //Διαγραφή φίλου
    @PUT //deleted-> id χρήστη που θέλει να διαγράψει
    @Path("/deletefriend/{id}&{deleted}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteFriends(@PathParam("id") String id, @PathParam("deleted") String deleted) {

        message = user.deletefromFriends(id, deleted);
        return user.getRes(message);
    }
    
    //Δημιουργία post
    @GET //posters_id->Αυτος που ποσταρει , posted_id-> σε ποιανου το profile , post-> περιεχόμενο
    @Path("/addpost/{posters_id}&{posted_id}&{post}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPost(@PathParam("posters_id") String posters_id, @PathParam("posted_id") String posted_id, @PathParam("post") String post) {
        message = user.addPost(posters_id, posted_id, post);
        return user.getRes(message);
    }

    //Τροποποίηση προφίλ
    @PUT //field->πεδίο που θελει να αλλάξει , content->περιεχόμενο
    @Path("/editmyprofile/{id}&{field}&{content}") //Επιλογή πεδίου για αλλαγή και περιεχόμενο
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editProfile(@PathParam("id") String id, @PathParam("field") String field, @PathParam("content") String content) {
        message = user.editMyProfile(id, field, content);
        return user.getRes(message);
    }

    //Διαγραφή ποστ
    @PUT //desc->περιεχόμενο post που θα διαγράψει
    @Path("/deletepost/{id}&{desc}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deletePost(@PathParam("id") String id, @PathParam("desc") String desc) {
        message = user.delPost(id, desc);
        return user.getRes(message);
    }

    //Τροποποίηση ποστ
    @PUT //
    @Path("/editpost/{id}&{desc}&{content}") // Επιλογή ποστ και αλλαγή 
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPost(@PathParam("id") String id, @PathParam("desc") String desc, @PathParam("content") String content) {
        message = user.editPost(id, desc, content);
        return user.getRes(message);
    }

    //Εγγραφή λογαριασμού
    @PUT
    @Path("/add/{id}&{pass}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAccount(@PathParam("id") String id, @PathParam("pass") String pass) throws JSONException {
        message = user.adduser(id, pass);
        return user.getRes(message);
    }

    //Είσοδος χρήστη
    @PUT
    @Path("/login/{id}&{pass}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("id") String id, @PathParam("pass") String pass) throws JSONException {
        message = user.login(id, pass);
        return user.getRes(message);
    }
}
