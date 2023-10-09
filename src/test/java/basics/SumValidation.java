package basics;

import files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourses(){


        int sum = 0;
        JsonPath js = new JsonPath(PayLoad.CoursePrice());
        int count = js.getInt("courses.size()");

        for (int i=0; i<count; i++){
            int price = js.get("courses["+i+"].price");
            int copies = js.get("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println("amount = " + amount);
            sum = sum + amount;
        }
        System.out.println("sum = " + sum);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");

        Assert.assertEquals(sum, purchaseAmount);
    }

}
