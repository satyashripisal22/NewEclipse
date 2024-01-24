package api.endpoints;

public class Routes {

	
	public static String base_url ="https://petstore.swagger.io/v2";
	//UserModule urls
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String put_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";
	
	//pet module urls
	public static String post_pet_url= base_url + "/pet";
	public static String get_pet_url= base_url + "/pet/{petId}";
	public static String put_pet_url= base_url + "/pet";
	public static String delete_pet_url= base_url + "/pet/{petId}";
	
	//Store Module urls
	
	
}
