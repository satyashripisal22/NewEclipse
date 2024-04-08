package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;

import io.restassured.response.Response;

public class UserTestDD {
	User userPayload;
	
	@Test(priority=1,dataProvider="Data", dataProviderClass=api.utilities.DataProviders.class,enabled=true)
	public void testCreateUser(String userID,String userName,String fName, String lName,String useremail,String password,String phone) throws Exception
	{
	    userPayload = new User();
	    userPayload.setId(Integer.parseInt(userID));
	    userPayload.setUsername(userName);
	    userPayload.setFirstname(fName);
		userPayload.setLastname(lName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		//userPayload.setUserstatus(Integer.parseInt(userStatus));
		Response response = UserEndPoints.createUser(userPayload);
		
		//log response
		response.then().log().all();
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);	
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=api.utilities.DataProviders.class,enabled=true)
	public void testGetUserData(String username) {
		Response response = UserEndPoints.GetUser(username);
		System.out.println("Read User Data");
		//log response
		response.then().log().all();
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3,dataProvider="UserNames",dataProviderClass=api.utilities.DataProviders.class,enabled=true)
	public void testUpdateUser(String userName) {
		
		userPayload.setUsername(userName);
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
	
	@Test(priority=2,enabled=true,dataProvider="UserNames",dataProviderClass=api.utilities.DataProviders.class)
	public void testDeleteUser(String username) {
		Response response = UserEndPoints.deleteUser(username);
		System.out.println("Delete User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
