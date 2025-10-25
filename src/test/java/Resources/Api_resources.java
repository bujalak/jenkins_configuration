package Resources;

public enum Api_resources {
	
	AddBooksAPI("Library/Addbook.php"),
	GetBooksAPI("Library/Addbook.php	"),
	DeleteBooksAPI("Library/DeleteBook.php");

	public  String resource;

	Api_resources(String resource) {
		
		this.resource = resource;
	}
	
	public String getresource() {
		return resource;
	}

}
