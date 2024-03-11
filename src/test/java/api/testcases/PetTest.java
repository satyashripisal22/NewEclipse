package api.testcases;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import api.payload.User;
import io.restassured.response.Response;

public class PetTest {
	Pet petpayload;
	Pet.Category categoryObj;
	Pet.Tag tagObj;
	Faker faker;
	@BeforeClass
public void generateTestData() {
	 petpayload =  new Pet();
	 faker = new Faker();
	 categoryObj = new Pet.Category();
	 categoryObj.setId(faker.idNumber().hashCode());
	 categoryObj.setName(faker.name().name()); 
	 
	 tagObj = new Pet.Tag();
	 tagObj.setId(faker.idNumber().hashCode());
     tagObj.setName(faker.name().name()); 
	
	
   // userPayload = new User();
    
    petpayload.setId(faker.idNumber().hashCode());
    petpayload.setName(faker.name().name());
    
    petpayload.setCategory(categoryObj);

    petpayload.setName("doggie");
    petpayload.setPhotoUrls(List.of(faker.name().name()));
    
    petpayload.setStatus("available");
   
    petpayload.setTags(List.of(tagObj));
   
}
	
	
	@Test
	public void testCreatePet() {
		
		Response response = PetEndPoints.CreatePet(petpayload);
		
		response.then().log().all();
	}
}
