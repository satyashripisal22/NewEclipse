package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import api.utilities.ExcelDataProvider;
import io.restassured.response.Response;

public class UserTestDD {
	User userPayload;
	
	@Test(priority=1,dataProvider="AllData", dataProviderClass=api.utilities.DataProviders.class,enabled=true)
	public void testCreateUser(String UserID,String userName,String FirstName, String LastName,String Email,String Password,String Phone) throws Exception
	{
	    userPayload = new User();
	    userPayload.setId(Integer.parseInt(UserID));
	    userPayload.setUsername(userName);
	    userPayload.setFirstname(FirstName);
		userPayload.setLastname(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		//userPayload.setUserstatus(Integer.parseInt(userStatus));
		Response response = UserEndPoints.createUser(userPayload);
		
		//log response
		response.then().log().all();
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);	
	}
	
	@Test(priority=2,dataProvider="UserNamesData",dataProviderClass=api.utilities.DataProviders.class,enabled=true)
	public void testGetUserData(String username) {
		Response response = UserEndPoints.GetUser(username);
		System.out.println("Read User Data");
		//log response
		response.then().log().all();
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3,dataProvider="UserNamesData",dataProviderClass=api.utilities.DataProviders.class,enabled=true)
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
	
	@Test(priority=2,enabled=true,dataProvider="UserNamesData",dataProviderClass=api.utilities.DataProviders.class)
	public void testDeleteUser(String username) {
		Response response = UserEndPoints.deleteUser(username);
		System.out.println("Delete User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
