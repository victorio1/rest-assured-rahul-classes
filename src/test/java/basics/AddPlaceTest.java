package basics;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddPlaceTest {

    public static void main(String[] args) {

        // validar is agregar un lugar esta trabajando como se espera xd

        String baseUrl = "https://rahulshettyacademy.com";
        String scopeExpected = "APP";
        String serverExpected = "Apache/2.4.52 (Ubuntu)";

        RestAssured.baseURI = baseUrl;
        // Todo declarado en un string para poder obtener la informacion luego xd
        String response = given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-type","application/json")
                .body(PayLoad.AddPlace()).when().post("maps/api/place/add/json")
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

        // Todo se puede desglosar la informacion mediante jsonPath
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");

        System.out.println(placeId);
        String newAddress = "Miami 318, United States";

        given()
                .log()
                .all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"place_id\": \""+placeId+"\",\n" +
                        "    \"address\": \""+newAddress+"\",\n" +
                        "    \"key\": \"qaclick123\"\n" +
                        "}")
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat()
                .log()
                .all()
                .statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        String getPlaceResponse = given()
                .log()
                .all()
                .queryParam("key","qaclick123")
                .queryParam("place_id",placeId)
                .header("Content-Type", "application/json")
                .when()
                .get("maps/api/place/get/json")
                .then()
                .assertThat()
                .log()
                .all()
                .statusCode(200)
                .body("name",equalTo("Eduardo Victorio Academy"))
                .extract()
                .response()
                .asString();

        JsonPath js1 = new JsonPath(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);

        // Cucumber Junit, Testng




    }

}
