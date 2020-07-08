package helloworldchallenge.steps;

import framework.BaseEntity;
import framework.rest.RestApiService;
import framework.rest.RestClientResponse;
import helloworldchallenge.entity.User;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import org.testng.asserts.SoftAssert;

public class AddNewClientSteps {

    @Step("Add new client with username '{user.username}' and fullname '{user.fullName}'")
    public static void addNewClient(User user) {
        String body = String.format(BaseEntity.getBody("newClientPost"), user.getFullName(), user.getUsername());
        RestClientResponse response = RestApiService.runPostRequest(BaseEntity.getPath("newClientPost"), body);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK,
                String.format("Expected status code: %d, actual status code: %s", HttpStatus.SC_OK, response.getStatusCode()));
        softAssert.assertEquals(response.getBody(),BaseEntity.getResponse("newClientPost"),
                 String.format("Expected response: %s, actual response: %s", BaseEntity.getResponse("newClientPost"), response.getBody()));
        softAssert.assertAll();
    }
}
