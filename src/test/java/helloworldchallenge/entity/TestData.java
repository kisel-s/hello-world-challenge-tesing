package helloworldchallenge.entity;

import framework.Logger;
import helloworldchallenge.steps.AddNewClientSteps;
import helloworldchallenge.steps.LoginSteps;
import org.apache.commons.lang3.RandomStringUtils;

public class TestData {
    private static final int LENGTH = 10;
    private static Logger logger = Logger.getInstance();

    public static User getUser() {
        return User.builder()
                .username(RandomStringUtils.randomAlphabetic(LENGTH))
                .fullName(RandomStringUtils.randomAlphabetic(LENGTH))
                .build();
    }

    public static User getLoggedInUser() {
        User user = getUser();
        logger.info(user.toString());
        AddNewClientSteps.addNewClient(user);
        LoginSteps.login(user);
        return user;
    }
}
