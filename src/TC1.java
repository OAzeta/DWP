import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class TC1 {
	
	public static Response getEndpoints(String endpoint)
	{
		RestAssured.baseURI= "https://bpdts-test-app.herokuapp.com";
		
		return given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
		       	.when().get(baseURI + endpoint)
		       	.then().contentType(ContentType.JSON).extract().response();
	}

	public static void main(String[] args) {
		
		String totalUsers = "/users";
		String cityUsers = "/city/London/users";
		
      Response response =  getEndpoints(totalUsers);
      List<String> noOfUsers = response.jsonPath().getList("$");
      System.out.print("\n Total number of users is " + noOfUsers.size());
      
      
      Response cityResponse =  getEndpoints(cityUsers);
      List<String> noOfCityUsers = cityResponse.jsonPath().getList("$");
      System.out.print("\n Total number of city users is " + noOfCityUsers.size());
      
      
	}

}
