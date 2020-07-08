package helloworldchallenge.steps;

import framework.BaseEntity;
import framework.rest.RestApiService;
import framework.rest.RestClientResponse;
import helloworldchallenge.entity.CustomErrorResponses;
import helloworldchallenge.entity.CustomHeader;
import helloworldchallenge.entity.User;
import io.qameta.allure.Step;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.message.BasicHeader;
import org.testng.asserts.SoftAssert;

public class ReceiveHelloMessageSteps {

    @Step("Receive hello message by exist and logged in user with username '{user.username}'")
    public static void receiveHelloMessage(User user) {
        Header header = new BasicHeader(CustomHeader.X_SESSION_ID.getValue(), user.getSessionId());
        RestClientResponse response = RestApiService.runGetRequest(BaseEntity.getPath("helloGet"), header);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                String.format("Expected status code: %d, actual status code: %s", HttpStatus.SC_OK, response.getStatusCode()));
        String actualResponse = String.format(BaseEntity.getResponse("helloGet"), user.getFullName());
        softAssert.assertEquals(response.getBody(), actualResponse,
                String.format("Expected response: %s, actual response: %s", response.getBody(), actualResponse));
        softAssert.assertAll();
    }

    @Step("Receive hello message for unauthorized user")
    public static void receiveHelloMessageForUnauthorized(User user) {
        Header header = new BasicHeader(CustomHeader.X_SESSION_ID.getValue(), user.getSessionId());
        RestClientResponse response = RestApiService.runGetRequest(BaseEntity.getPath("helloGet"), header);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNAUTHORIZED,
                String.format("Expected status code: %d, actual status code: %s", HttpStatus.SC_UNAUTHORIZED, response.getStatusCode()));
        String actualResponse = response.getBody();
        softAssert.assertEquals(CustomErrorResponses.UNAUTHORIZED, actualResponse,
                String.format("Expected response: %s, actual response: %s", CustomErrorResponses.UNAUTHORIZED, actualResponse));
        softAssert.assertAll();
    }
}
