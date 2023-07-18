package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	
	public Logger logger;

	@BeforeClass
	public void setup() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void testPostuser() {
		
		logger.info("************Creating user**************");
		Response response = UserEndPoints.CreateUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************User info Display **************");
	}

	//@Test(priority = 2)
	public void testGetUserName() {
		Response res = UserEndPoints.ReadUser(userPayload.getUserName());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority= 3)
	public void TestUpdateUser() {
		logger.info("************Updating user**************");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.UpdateUser(this.userPayload.getUserName(), userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************ User Updated**************");
	}
	
	@Test(priority = 4)
	public void TestDeleteUser() {
		
		Response response=UserEndPoints.DeleteUser(this.userPayload.getUserName());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
