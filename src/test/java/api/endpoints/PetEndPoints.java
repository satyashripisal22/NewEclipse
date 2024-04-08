package api.endpoints;

import api.payload.Pet;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {

	public static Response CreatePet(Pet payload) {
		
		Response response= given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)
				
				.when()
				.post(Routes.post_url);
				return response;
				
	}

  public static Response GetUser(String Name){
		
		Response response = given()
				.accept(ContentType.JSON)
	
				.pathParam("username", Name)
				
				.when()
				.get(Routes.get_pet_url);
		return response;
	}
}
