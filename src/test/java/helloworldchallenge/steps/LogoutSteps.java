package helloworldchallenge.steps;

import framework.BaseEntity;
import framework.rest.RestApiService;
import framework.rest.RestClientResponse;
import helloworldchallenge.entity.User;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import org.testng.asserts.SoftAssert;

public class LogoutSteps {

    @Step("Log out user with username '{user.username}'")
    public static void logout(User user) {
        String body = String.format(BaseEntity.getBody("logoutPost"), user.getUsername());
        RestClientResponse response = RestApiService.runPostRequest(BaseEntity.getPath("logoutPost"), body);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK,
                String.format("Expected status code: %d, actual status code: %s", HttpStatus.SC_OK, response.getStatusCode()));
        softAssert.assertEquals(response.getBody(),BaseEntity.getResponse("loginPost"),
                String.format("Expected response: %s, actual response: %s", BaseEntity.getResponse("loginPost"), response.getBody()));
        softAssert.assertAll();
    }
}
