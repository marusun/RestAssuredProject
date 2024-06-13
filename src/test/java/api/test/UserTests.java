package api.test;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;

import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;

import io.restassured.response.Response;

public class UserTests {
	Faker fake;
	User userPayload;

	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		fake = new Faker();
		logger=org.apache.logging.log4j.LogManager.getLogger(this.getClass());
		userPayload = new User();
	
		userPayload.setId(fake.idNumber().hashCode());
		userPayload.setUsername(fake.name().username());
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().emailAddress());
		userPayload.setPassword(fake.internet().password(5, 10));
		userPayload.setPhone(fake.phoneNumber().cellPhone());

	}

	@Test(priority = 1)
	public void testUserPost() {
		logger.info("---------------creatingUser------------");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("---------------user created ------------");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("---------------Reading User------------");
		Response response = UserEndPoints.readUser(userPayload.getUsername());
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("---------------user Reading successfull------------");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("---------------updating User------------");
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().emailAddress());
		Response response = UserEndPoints.updateUser(userPayload.getUsername(),userPayload);
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("---------------updation of User done------------");
		Response responseAfterUpdate = UserEndPoints.readUser(userPayload.getUsername());
		response.then().log().all();

		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("---------------removing User------------");
		Response response = UserEndPoints.deleteUser(userPayload.getUsername());
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("--------------- User removed------------");
	}
	
}
