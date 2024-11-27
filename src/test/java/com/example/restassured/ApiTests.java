package com.example.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void testGetPosts() {
        RestAssured
            .given()
            .baseUri(BASE_URL)
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1));
    }

    @Test
    public void testCreatePost() {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";


        RestAssured
            .given()
            .baseUri(BASE_URL)
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("title", equalTo("foo"));
    }

    @Test
    public void testUpdatePost() {
        String requestBody = "{ \"title\": \"updated\", \"body\": \"bar\", \"userId\": 1 }";


        RestAssured
            .given()
            .baseUri(BASE_URL)
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .put("/posts/1")
            .then()
            .statusCode(200)
            .body("title", equalTo("updated"));
    }

    @Test
    public void testDeletePost() {
        RestAssured
            .given()
            .baseUri(BASE_URL)
            .when()
            .delete("/posts/1")
            .then()
            .statusCode(200);
    }

    @Test
    public void testGetInvalidPost() {
        RestAssured
            .given()
            .baseUri(BASE_URL)
            .when()
            .get("/posts/99999")
            .then()
            .statusCode(404);
    }
}