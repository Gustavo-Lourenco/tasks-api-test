package br.ce.glourenco.tasks.api.test;

import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class APITest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveRetornarTarefas() {
        given()
            .log().all()
        .when()
            .get("/todo")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefaComSucesso(){
        given()
            .body("{ \"task\": \"Teste via API\", \"dueDate\": \"2020-12-13\" }")
        .when()
            .post("/todo")
        .then()
            .log().all()
            .statusCode(201);
    }

    @Test
    public void naoDeveAdicionarTarefaInvalida(){
        given()
            .body("{ \"task\": \"Teste via API\", \"dueDate\": \"2010-12-13\" }")
        .when()
            .post("/todo")
        .then()
            .log().all()
            .statusCode(400)
            .body("message", is("Due date must be in past"));
    }

}

