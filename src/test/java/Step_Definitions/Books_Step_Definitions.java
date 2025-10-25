package Step_Definitions;

import org.apache.http.protocol.ResponseServer;

import Resources.Api_resources;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given; // Import for given()


public class Books_Step_Definitions {
	
	Response response;
	RequestSpecification request;
	ResponseSpecification rep;
     JsonObject requestparams;
     
     String id;
     
     
     public Books_Step_Definitions() {
 		request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType("application/json").build();

    	 
     }
	
	
	@Given("Add the {string} with isbn {string} and aisle {string} and author {string}")
    public void addBookPayload(String bookName, String isbn, String aisle, String author) {
		
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType("application/json").build();
		
		
		
		//request= RestAssured.given().header("Content-Type", "application/json");
		requestparams = new JsonObject();
		requestparams.add("name", bookName);
		requestparams.add("isbn", isbn);
		requestparams.add("aisle", aisle);
		requestparams.add("author", author);
		}
	 @When("User calls {string} payload")
	 public void userCallsPayload(String endpoint) {
		 
		 Api_resources api = Api_resources.valueOf(endpoint);
	       // response = request.body(requestparams.toString()).when().post(api.getresource());
	        response = given().spec(request).body(requestparams.toString()).when().post(api.getresource());

	        
	        
	    }
	  
	  @Then("User calls the request to verify status code {string}")
	    public void verifyStatusCode(String statusCode) {
		  
		  response.then().assertThat().statusCode(Integer.parseInt(statusCode));
		  System.out.println(response.asString());
		  System.out.println(response.jsonPath().getString("ID"));
		  id =response.jsonPath().getString("ID");
	 
		  
	 

	}
	  
	  @Given("User calls the books with queryparams")
	    public void user_calls_the_books_with_query_params() {
		  
		 request= given().spec(request).queryParam("ID", id);
	  }
		  
		  
		  
	  
	         
	    

	    @When("User calls the books with {string} payload")
	    public void user_verifies__payload(String apiName) {
			 Api_resources api = Api_resources.valueOf(apiName);
			 response = given().spec(request).when().get(api.getresource());

	    	
	    	
	      
	    	
	    	
	    }
	        
	    

	    @Then("User validates the fields in the response")
	    public void user_validates_the_fields_in_the_response() {
	     
	    	
	    	response.then().assertThat().statusCode(200);
	    	
	    	System.out.println(response.asString());
	    }
	  
	
	

}
