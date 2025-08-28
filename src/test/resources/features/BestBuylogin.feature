Feature: User operations on BestBuy
  @L1
  Scenario: Creating a new user
    Given  start with the BestBuy home page
    Then I click on Account button
    And  I see the panel with Create account button
    When I click on the Create Account button
    Then I should be navigated to Create Account page
    And I can enter all of my account information
    Then I should be navigated to my account page

<<<<<<< HEAD
  @L3
  Scenario Outline: Password Validations
    Given  start with the BestBuy home page
    Then I click on Account button
    And  I see the panel with Create account button
    When I click on the Create Account button
    Then I should be navigated to Create Account page
    When I enter <password> it should check for password validations <Valid>

    Examples:
      | password     | Valid |
      |  "QEA@team2" | "true" |
      | "JohnDoe$23" |"true"  |
      | "john"       |"false" |
=======
    @L3
    Scenario Outline: Password Validations
      Given  start with the BestBuy home page
      Then I click on Account button
      And  I see the panel with Create account button
      When I click on the Create Account button
      Then I should be navigated to Create Account page
      When I enter <password> it should check for password validations <Valid>

      Examples:
        | password     | Valid |
        |  "QEA@team2" | "true" |
        | "JohnDoe$23" |"true"  |
        | "john"       |"false" |

>>>>>>> c3cf49ccadfcd748bdefc4d01c03ff95aac1c06e
