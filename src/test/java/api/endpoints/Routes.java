package api.endpoints;

/*
 * https://petstore.swagger.io/v2/user/createWithList
 */
public class Routes {
	
public static String base_url="https://petstore.swagger.io/v2/";



/*
 * BaseURL+EndPoints=URL(unified resourse Identifier)
 * endPoins for User Module
 */
public static String post_url=base_url+"user";

public static String get_url=base_url+"user/{username}";

public static String update_url=base_url+"user/{username}";

public static String delete_url=base_url+"user/{username}";


}
