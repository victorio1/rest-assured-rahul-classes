package basics;

import files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath js = new JsonPath(PayLoad.CoursePrice());

        int count = js.getInt("courses.size()");
        System.out.println("count = " + count);
        
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("totalAmount = " + totalAmount);
        
        String titleFirstCourse = js.get("courses[0].title");
        System.out.println("titleFirstCourse = " + titleFirstCourse);

        for (int i=0; i<count; i++){
            String coursesTitles = js.get("courses["+i+"].title");
            int coursePrices = js.get("courses["+i+"].price");
            System.out.println("coursesTitles = " + coursesTitles);
            System.out.println("coursePrices = " + coursePrices);
        }

        System.out.println("Print Nro Of Copies Sold By RPA Course");

        for (int i=0; i<count; i++){
            String coursesTitles = js.get("courses["+i+"].title");
            String courseRPA = "RPA";
            if (coursesTitles.equalsIgnoreCase(courseRPA)){
                int copies = js.get("courses["+i+"].copies");
                System.out.println("copies = " + copies);
                break;
            }
        }

    }

}
