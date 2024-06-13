package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDUserTests {

	Faker fake;
	@Test(priority = 1, dataProvider = "AllData", dataProviderClass = DataProviders.class)

	public void testPostUser(String UserId, String Username, String fn, String ls, String email, String password,
			String phone) {
		fake = new Faker();
		User userPayload = new User();
		userPayload.setId(fake.idNumber().hashCode());
		userPayload.setUsername(Username);
		userPayload.setFirstName(fn);
		userPayload.setLastName(ls);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);

		Response response = UserEndPoints.createUser(userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)

	public void testDeleteUser(String Username) {

		
		Response response = UserEndPoints.deleteUser(Username);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

}
