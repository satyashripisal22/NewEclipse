package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {

	User userPayload;
	@Test(priority=1,dataProvider="AllData", dataProviderClass=DataProviders.class)
	public void testCreateUser(int userId,String UserName,String firstName,String lastName,String email,String pwd,String phone) {
		 
	    userPayload = new User();
	    
	    userPayload.setId(userId);
	    userPayload.setUsername(UserName);
	    userPayload.setFirstname(firstName);
		userPayload.setLastname(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		//userPayload.setUserstatus(Integer.parseInt(userStatus));
		
		Response response = UserEndPoints.createUser(userPayload);
		
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2,dataProvider = "UserNamesData", dataProviderClass = DataProviders.class,enabled=true)
	public void testGetUserData() {
		
		Response response = UserEndPoints.GetUser(this.userPayload.getUsername());
		System.out.println("Read User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3,dataProvider="UserNameData", dataProviderClass=DataProviders.class,enabled=true)
	public void testUpdateUser(String fname) {
		
		userPayload.setFirstname(fname);
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Read User data to check if firstname is updated
		
		Response responsepostupdate = UserEndPoints.GetUser(this.userPayload.getUsername());
		System.out.println("Update User Data");
		responsepostupdate.then().log().all();
		
		
  
	}
	
	@Test(priority=2,enabled=false,dataProvider="UserNameData", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String username) {
		
		
		Response response = UserEndPoints.deleteUser(username);
		System.out.println("Delete User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
