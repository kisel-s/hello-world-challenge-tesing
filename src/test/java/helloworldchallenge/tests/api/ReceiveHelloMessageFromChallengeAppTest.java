package helloworldchallenge.tests.api;

import helloworldchallenge.entity.TestData;
import helloworldchallenge.entity.User;
import helloworldchallenge.steps.LogoutSteps;
import helloworldchallenge.steps.ReceiveHelloMessageSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class ReceiveHelloMessageFromChallengeAppTest {
    private static User user;

    @Test
    public void receiveHelloMessageForExistAndLoginUser() {
        user = TestData.getLoggedInUser();
        ReceiveHelloMessageSteps.receiveHelloMessage(user);
    }

    @Test
    public void receiveHelloMessageForExistAndUnauthorizedUser() {
        user = TestData.getLoggedInUser();
        LogoutSteps.logout(user);
        ReceiveHelloMessageSteps.receiveHelloMessageForUnauthorized(user);
    }

    @Test
    public void receiveHelloMessageForWithWrongSessionIDFormat() {
        user = TestData.getLoggedInUser();
        user.setSessionId("0");
        ReceiveHelloMessageSteps.receiveHelloMessageForUnauthorized(user);
    }

    @Test
    public void receiveHelloMessageForWithWrongSessionID() {
        user = TestData.getLoggedInUser();
        user.setSessionId(UUID.randomUUID().toString());
        ReceiveHelloMessageSteps.receiveHelloMessageForUnauthorized(user);
    }

    @AfterMethod
    private void after(){
        LogoutSteps.logout(user);
    }
}
