@api
  Feature: Pet API

    Scenario Outline: Find pets by status
      Given A request to get the pets with status "<status>" is executed
      And The status code should be 200
      Then The pets information is saved on the PetsData file
      And The information related to the amount of pets with the same name is saved on the PetsWithSameName file
      Examples:
        | status |
        | sold   |


