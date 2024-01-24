package apiauto;


import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import netscape.javascript.JSObject;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class APITest {
    private String urlApi;
    private AtributDataUsers dataUsers;

    @BeforeTest
    public void setUp(){
        urlApi = "https://reqres.in/";
        RestAssured.baseURI = urlApi;
        dataUsers = new AtributDataUsers();
    }

    @Test
    public void getUsersTest(){
        // Test get api data in page one, after that get 6 data user in one page
        System.out.println("\n\nTest get Data User");
        String pathTest = "api/users?page=1";

        //expected result
        int statusResponseExpected = 200;
        int pageExpected = 1;
        int totalDataExpected = 6;

        // request API
        given().when().get(pathTest)
                .then()
                .log().all() // print the entire response to console (optional)
                .assertThat().statusCode(statusResponseExpected) // check status response
                .assertThat().body("page", Matchers.equalTo(pageExpected)) // check body response
                .assertThat().body("data.id", Matchers.hasSize(totalDataExpected)); // check total data response
    }

    @Test
    public void putUserTest(){
        // Test put api user data, check data has been update
        System.out.println("\n\nTest put User");

        //expected result
        int statusResponseExpected = 200;

        // data to update
        int userID = 2;
        String newName = "Update User";
        String pathTest = "api/users/" + userID;

        // get data user which user id 2
        String firstName = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathFirstName);
        String lastName = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathLastName);
        String avatar = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathAvatar);
        String email = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathEmail);

        System.out.println("name before update = " + firstName);

        // Change the first name to "update user"
        // Create body request with HasMap and convert it to json
        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put(dataUsers.pathId, userID);
        bodyMap.put(dataUsers.pathFirstName, newName);
        bodyMap.put(dataUsers.pathLastName, lastName);
        bodyMap.put(dataUsers.pathEmail, email);
        JSONObject jsObject = new JSONObject(bodyMap);

        // request API
        given().log().all()
                .header("Content-Type", "application/json") // set the header tp accept json
                .body(jsObject.toString()) // convert jsonObject tp string format
                .put(pathTest)
                .then().log().all() // print the entire response to console (optional)
                .assertThat().statusCode(statusResponseExpected) // check status response
                .assertThat().body(dataUsers.pathFirstName, Matchers.equalTo(newName)); // check firstname has been update
    }

    @Test
    public void putPatchTest(){
        // Test put api user data, check data has been update
        System.out.println("\n\nTest patch User");

        //expected result
        int statusResponseExpected = 200;

        // data to update
        int userID = 3;
        String newName = "Update User";
        String pathTest = "api/users/" + userID;

        // get data user which user id 3
        String firstName = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathFirstName);
        System.out.println("name before update = " + firstName);

        // Change the first name to "update user"
        // Create body request with HasMap and convert it to json
        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put(dataUsers.pathFirstName, newName);
        JSONObject jsObject = new JSONObject(bodyMap);

        // request API
        given().log().all()
                .header("Content-Type", "application/json") // set the header tp accept json
                .body(jsObject.toString()) // convert jsonObject tp string format
                .patch(pathTest)
                .then().log().all() // print the entire response to console (optional)
                .assertThat().statusCode(statusResponseExpected) // check status response
                .assertThat().body(dataUsers.pathFirstName, Matchers.equalTo(newName)); // check firstname has been update
    }

    @Test
    public void putDeleteTest(){
        // Test put api user data, check data has been update
        System.out.println("\n\nTest delete User");

        //expected result
        int statusResponseExpected = 204;

        // data to delete
        int userID = 4;
        String pathTest = "api/users/" + userID;

        // get data user which user id 4
        String firstName = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathFirstName);
        String lastName = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathLastName);
        String avatar = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathAvatar);
        String email = given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathEmail);

        System.out.println("Data user before delete");
        System.out.println("First name = " + firstName);
        System.out.println("last name = " + lastName);
        System.out.println("avatar name = " + avatar);
        System.out.println("email name = " + email);

        // request API delete
        given().log().all()
                .delete(pathTest)
                .then().log().all() // print the entire response to console (optional)
                .assertThat().statusCode(statusResponseExpected); // check status response

        // request API get to cek data has been delete
        System.out.println("\ntest get after delete");
        System.out.println(given().when().get(pathTest).getBody().jsonPath().getString(dataUsers.nameObject + "." + dataUsers.pathFirstName));
    }

    @Test
    public void getSingleUserTest(){
        // Test get api data in page one, after that get 6 data user in one page
        System.out.println("\n\nTest get Single User");

        //expected result
        int statusResponseExpected = 200;
        int userID = 5;
        String pathTest = "api/users/" + userID;
        // file json to compare
        File fileJson = new File("src/test/resources/jsonSchema/GetSingleUserSchema.json");

        // request API Get api/users/5
        given().log().all()
                .when().get(pathTest)
                .then().log().all() // print the entire response to console (optional)
                .assertThat().statusCode(statusResponseExpected) // check status response
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(fileJson)); // check body response

    }
}
