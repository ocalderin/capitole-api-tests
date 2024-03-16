package com.capitole.apitests.api.steps;

import com.capitole.apitests.frameworkauto.utils.FileUtils;
import com.capitole.apitests.frameworkauto.utils.RestUtils;
import com.capitole.apitests.frameworkauto.utils.TestContext;
import com.capitole.apitests.model.User;
import com.capitole.apitests.utils.Routes;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class UsersApiSteps {
    private static final String USER_DATA_FILENAME = "UserData";
    private Response response;

    private TestContext testContext;

    public UsersApiSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @DataTableType
    public User userEntryTransformer(Map<String, String> row) {
        return new User(
                Integer.parseInt(row.get("id")),
                row.get("username"),
                row.get("firstName"),
                row.get("lastName"),
                row.get("email"),
                row.get("password"),
                row.get("phone"),
                Integer.parseInt(row.get("userStatus"))
        );
    }
    @Given("A request to created an user is executed")
    public void aRequestToCreatedAnUserIsExecuted(final User user) {
        response = RestUtils.doPost(Routes.ROUTE_CREATE_USER, user);
        testContext.getScenarioContext().setContext("RESPONSE", response);
    }

    @When("A request to get the user with username {string} is executed")
    public void aRequestToGetTheUserWithUsernameIsExecuted(String username) {
        response = RestUtils.doGet(String.format(Routes.ROUTE_GET_USER_BY_USERNAME, username));
        testContext.getScenarioContext().setContext("RESPONSE", response);
    }

    @Then("The user information should be:")
    public void theUserInformationShouldBe(final User expectedUser) {
        response = (Response) testContext.getScenarioContext().getContext("RESPONSE");
        final User currentUser = response.as(User.class);
        Assertions.assertEquals(expectedUser.getId(), currentUser.getId(), String.format("The id should be %s, but it is %s",
                expectedUser.getId(), currentUser.getId()));
        Assertions.assertEquals(expectedUser.getUserStatus(), currentUser.getUserStatus(), String.format("The user status should be %s, " +
                        "but it is %s", expectedUser.getUserStatus(), currentUser.getUserStatus()));
        Assertions.assertEquals(expectedUser.getEmail(), currentUser.getEmail(), String.format("The email should be %s, but it is %s",
                expectedUser.getEmail(), currentUser.getEmail()));
        Assertions.assertEquals(expectedUser.getUsername(), currentUser.getUsername(), String.format("The username should be %s, but it is %s",
                expectedUser.getUsername(), currentUser.getUsername()));
        Assertions.assertEquals(expectedUser.getFirstName(), currentUser.getFirstName(), String.format("The firstname should be %s, but it is %s",
                expectedUser.getFirstName(), currentUser.getFirstName()));
        Assertions.assertEquals(expectedUser.getLastName(), currentUser.getLastName(), String.format("The lastname should be %s, but it is %s",
                expectedUser.getLastName(), currentUser.getLastName()));
        Assertions.assertEquals(expectedUser.getPassword(), currentUser.getPassword(), String.format("The password should be %s, but it is %s",
                expectedUser.getPassword(), currentUser.getPassword()));
        Assertions.assertEquals(expectedUser.getPhone(), currentUser.getPhone(), String.format("The phone should be %s, but it is %s",
                expectedUser.getPhone(), currentUser.getPhone()));
    }

    @And("The user information is saved on the UserData file")
    public void theUserInformationIsSavedOnTheUserDataFile() {
        response = (Response) testContext.getScenarioContext().getContext("RESPONSE");
        FileUtils.saveResponseToFile(response, USER_DATA_FILENAME);
    }
}
