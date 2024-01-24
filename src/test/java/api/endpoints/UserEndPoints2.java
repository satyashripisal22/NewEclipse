package api.endpoints;

import api.payload.User;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2{

	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("Routes");//load Routes.properties file
	    return routes;
	}
	public static Response createUser(User payload){
		String post_url  = getURL().getString("post_url");
	Response response= given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		return response;
	}
	
	public static Response GetUser(String userName){
		String get_url  = getURL().getString("get_url");
		Response response = given()
				.accept(ContentType.JSON)
	
				.pathParam("username", userName)
				
				.when()
				.get(Routes.get_url);
		return response;
	}
	
	public static Response updateUser(String userName,User payload) {
		String put_url  = getURL().getString("update_url");
		Response response= given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
			.when()
			.put(Routes.put_url);
			return response;
		}
	
public static Response deleteUser(String userName) {
	String delete_url  = getURL().getString("delete_url");
		Response response= given()
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			
			.when()
			.delete(Routes.delete_url);
			return response;
		}
	
	
}
