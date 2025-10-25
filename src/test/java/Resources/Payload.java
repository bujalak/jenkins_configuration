package Resources;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;

public class Payload {

	
	
	  public static String addBookPayload(String bookName, String isbn, String aisle, String author) {
	        JsonObject requestParams = new JsonObject();
	        requestParams.add("name", bookName);
	        requestParams.add("isbn", isbn);
	        requestParams.add("aisle", aisle);
	        requestParams.add("author", author);
	        return requestParams.toString();
	    }
}
