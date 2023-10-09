package basics;

import files.PayLoad;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DynamicJson {


    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle){

        String bookUrl = "http://216.10.245.166";
        String path = "/Library/Addbook.php";
        String response = "";

        RestAssured.baseURI = bookUrl;
        response = given()
                .header("Content-Type", "application/json")
                .body(PayLoad.AddBook(isbn,aisle))
                .when()
                .post(path)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println("id = " + id);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){

        // Array = collection of elements;
        // mulitimensional array = collection of arrays;

        return new Object[][] {{"eduardito","9363"}, {"oscar","9856"}, {"tiago","8456"}};


    }


}
