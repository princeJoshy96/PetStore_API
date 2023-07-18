package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response CreateUser(User payload) {
		
		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);
		return res;
	}

	public static Response ReadUser(String UserName) {

		Response res = given().pathParam("username", UserName).when().get(Routes.get_url);
		return res;
	}

	public static Response UpdateUser(String UserName, User payload) {

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", UserName)
				.body(payload).when().put(Routes.update_url);
		return res;
	}
	
	public static Response DeleteUser(String UserName) {

		Response res = given().pathParam("username", UserName).when().delete(Routes.delet_url);
		return res;
	}
}
