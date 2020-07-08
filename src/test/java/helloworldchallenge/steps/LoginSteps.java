package helloworldchallenge.steps;

import framework.BaseEntity;
import framework.rest.RestApiService;
import framework.rest.RestClientResponse;
import helloworldchallenge.entity.CustomHeader;
import helloworldchallenge.entity.User;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LoginSteps {

    @Step("Log in user with username '{user.username}'")
    public static void login(User user) {
        String body = String.format(BaseEntity.getBody("loginPost"), user.getUsername());
        RestClientResponse response = RestApiService.runPostRequest(BaseEntity.getPath("loginPost"), body);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK,
                String.format("Expected status code: %d, actual status code: %s", HttpStatus.SC_OK, response.getStatusCode()));
        softAssert.assertEquals(response.getBody(),BaseEntity.getResponse("loginPost"),
                String.format("Expected response: %s, actual response: %s", BaseEntity.getResponse("loginPost"), response.getBody()));
        softAssert.assertAll();
        user.setSessionId(Arrays.stream(response.getHeaders()).filter(p -> p.getName().equals(CustomHeader.X_SESSION_ID.getValue()))
                .collect(Collectors.toList()).get(0).getValue());
    }
}
