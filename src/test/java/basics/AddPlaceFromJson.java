package basics;

import files.PayLoad;
import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceFromJson {

    public static void main(String[] args) throws IOException {

        // validar is agregar un lugar esta trabajando como se espera xd

        String baseUrl = "https://rahulshettyacademy.com";
        String scopeExpected = "APP";
        String serverExpected = "Apache/2.4.52 (Ubuntu)";
        String AddPlaceJsonPath = "C:\\Users\\artic\\This Pc\\Proyectos\\rest-assured-project-scratch\\src\\test\\resources\\JsonFiles\\AddPlace.json";

        RestAssured.baseURI = baseUrl;
        // Todo declarado en un string para poder obtener la informacion luego xd
        String response = given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-type","application/json")
                .body(new String(Files.readAllBytes(Paths.get(AddPlaceJsonPath))))
                .when()
                .post("maps/api/place/add/json")
                .then()
                .log()
                .all()
                // Todo Validacion del assert con hamcrest
                .assertThat()
                .statusCode(200)
                .body("scope", equalTo(scopeExpected))
                .header("server",serverExpected)
                // Todo Extraccion de informacion del response mediante un string
                .extract()
                .response()
                .asString();
        // Todo se puede reemplazar un sout con log y all para poder ver la informacion del json

        System.out.println(response);


    }

}
