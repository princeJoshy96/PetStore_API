package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {

	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");

		return routes;
	}

	public static Response CreateUser(User payload) {

		String post_url = getURL().getString("post_url");

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);
		return res;
	}

	public static Response ReadUser(String UserName) {

		String get_url = getURL().getString("get_url");

		Response res = given().pathParam("username", UserName).when().get(get_url);
		return res;
	}

	public static Response UpdateUser(String UserName, User payload) {

		String update_url = getURL().getString("update_url");

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", UserName)
				.body(payload).when().put(update_url);
		return res;
	}

	public static Response DeleteUser(String UserName) {

		String delete_url = getURL().getString("delet_url");

		Response res = given().pathParam("username", UserName).when().delete(delete_url);
		return res;
	}
}
