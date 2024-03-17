package com.capitole.apitests.api.steps;

import com.capitole.apitests.frameworkauto.utils.FileUtils;
import com.capitole.apitests.frameworkauto.utils.RestUtils;
import com.capitole.apitests.frameworkauto.utils.TestContext;
import com.capitole.apitests.model.Pet;
import com.capitole.apitests.model.PetInfo;
import com.capitole.apitests.utils.CountPets;
import com.capitole.apitests.utils.Routes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class PetApiSteps {
    private static final String PETS_DATA_FILENAME = "PetsData";
    private static final String PETS_WITH_SAME_NAME_DATA_FILENAME = "PetsWithSameName";
    private Response response;
    private TestContext testContext;

    public PetApiSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("A request to get the pets with status {string} is executed")
    public void aRequestToGetThePetsWithStatusIsExecuted(final String status) {
        response = RestUtils.doGet(String.format(Routes.ROUTE_GET_PETS_BY_STATUS, status));
        testContext.getScenarioContext().setContext("RESPONSE", response);
    }

    @Then("The pets information is saved on the PetsData file")
    public void thePetsInformationIsSavedOnThePetsDataFile() {
        response = (Response) testContext.getScenarioContext().getContext("RESPONSE");
        final List<Pet> pets = List.of(response.as(Pet[].class));
        List<PetInfo> info = new ArrayList<>();
        for (Pet pet : pets) {
            info.add(new PetInfo(pet.getId(), pet.getName()));
        }
        testContext.getScenarioContext().setContext("PETS_INFO", info);
        FileUtils.saveListToFile(info, PETS_DATA_FILENAME);
    }

    @And("The information related to the amount of pets with the same name is saved on the PetsWithSameName file")
    public void theInformationRelatedToTheAmountOfPetsWithTheSameNameIsSavedOnThePetsWithSameNameFile() {
        List<PetInfo> info = (List<PetInfo>) testContext.getScenarioContext().getContext("PETS_INFO");
        final CountPets countPets = new CountPets(info);
        FileUtils.saveMapToFile(countPets.count(), PETS_WITH_SAME_NAME_DATA_FILENAME);
    }
}
