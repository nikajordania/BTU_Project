import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.equalTo;

public class Task3Tests {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
    }

    @Test
    public void bookStoreV1BooksTest() {
        given()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("books[-1].isbn", equalTo("9781593277574"))
                .and()
                .body("books[0,1].pages", containsInRelativeOrder(234, 254));
    }
}
