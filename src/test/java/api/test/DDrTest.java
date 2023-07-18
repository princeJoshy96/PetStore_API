package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.dataProvider;
import io.restassured.response.Response;

public class DDrTest {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = dataProvider.class)
	public void testpostUser(String UserID, String UNAme, String FName, String LName, String Email, String pswd,
			String ph) {

		User userPayload = new User();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUserName(UNAme);
		userPayload.setFirstname(FName);
		userPayload.setLastname(LName);
		userPayload.setEmail(Email);
		userPayload.setPassword(pswd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPoints.CreateUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	//@Test(priority =2, dataProvider="UserNames", dataProviderClass=dataProvider.class)
	public void TestDeletebyName(String UName) {
		
		Response respone=UserEndPoints.DeleteUser(UName);
		Assert.assertEquals(respone.getStatusCode(), 200);
	}
}
