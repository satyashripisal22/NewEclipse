package api.testcases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;

import io.restassured.response.Response;

public class UserTest2 {
//validation
	//for generating test data
	Faker faker;
	User userPayload;
	public static Logger logger;
	
	@BeforeClass
	public void generateTestData() {
		
		faker = new Faker();
	    userPayload = new User();
	    
	    userPayload.setId(faker.idNumber().hashCode());
	    userPayload.setUsername(faker.name().username());
	    userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//obtain logger
		logger = LogManager.getLogger(UserTest2.class);
		
		
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		
		
		Response response = UserEndPoints2.createUser(userPayload);
		
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Create User executed");
		
	}
	
	@Test(priority=2)
	public void testGetUserData() {
		
		Response response = UserEndPoints2.GetUser(this.userPayload.getUsername());
		System.out.println("Read User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
				logger.info("Get User Data.");
				
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		
		userPayload.setEmail(faker.internet().emailAddress());//.setFirstname(faker.name().firstName());
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Read User data to check if firstname is updated
		
		Response responsepostupdate = UserEndPoints.GetUser(this.userPayload.getUsername());
		
		System.out.println("Update User Data");
		
		responsepostupdate.then().log().all();
		
		//log
		logger.info("Update User Data.");
		
		
  
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		System.out.println("Delete User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
				logger.info("Delete User Data.");
	}
	
	
	
}
