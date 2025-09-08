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




    @L3
    Scenario Outline: Password Validations
      Given  start with the BestBuy home page
      Then I click on Account button
      And  I see the panel with Create account button
      When I click on the Create Account button
      Then I should be navigated to Create Account page
      When I enter <password> it should check for password validations <Valid>

    @UC-105 @Positive Examples:
        | password     | Valid |
        |  "QEA@team2" | "true" |
        | "JohnDoe$23" |"true"  |
     @UC-106 @Negative Examples:
        |   password     | Valid  |
        |   "john"       | "false"|


  @L4
  Scenario: Create account without false “email exists”
    Given I open the Best Buy Create Account page
    And I accept all cookies if shown
    And I choose country "United States" if prompted
    When I enter a fresh, valid email
    And I enter first name "Kevin" and last name "Rivera"
    And I enter password "ValidPass1!"
    And I reenter password "ValidPass1!"
    And I enter mobile "7898787234"
    And I accept Terms and submit
    Then registration should proceed or show verify email
    Then I should be navigated to my account page

    @L5
    Scenario: login new user for Carters site
      Given I open the Carters home page
      When I click on the login button
      When I click on the Create Account button carters
      Then I should see a panel to enter account information for Carters
      And I can enter all of my account information for Carters


    @L7
    Scenario: Successfully create a new account on Gap site
      Given I launch the Gap sign-in page
      When I navigate to the Create Account section
      And I enter "John" in the First Name field
      And I enter "Doe" in the Last Name field
      And I enter "john.doe123@test.com" in the Email field
      And I enter "Password@123" in the Password field
      And I enter "Password@123" in the Confirm Password field
      And I check the "Keep me signed in" checkbox
      And I click the Create Account button
      Then I should be redirected to the My Account page

@L8
  Scenario: Successfully register a new user
    Given I navigate to the Demo Web Shop registration page
    When I select gender "Male"
    And I enter "John" into the First Name field
    And I enter "Doe" into the Last Name field
    And I enter a unique email into the Email field
    And I enter "SecurePass123!" into the Password field
    And I enter "SecurePass123!" into the Confirm Password field
    And I click the Register button
    Then I should see the message "Your registration completed"

   @UC-107 @Positive @L9
    Scenario: Successfully register a new Staples user
      Given I navigate to the Staples account creation page
      When I enter a unique email
      And I enter "Alexander" into the First Name field of staples
      And I enter "Steve" into the Last Name field of staples
      And I enter "7327121878" into the Phone Number field
      And I enter "SecurePass123!" into the Password field of staples
      And I select "Myself" for the shopping preference
      And I click the Create Account button of staples
      Then I should see a confirmation message or be redirected to My Account

     @UC-108 @Negative @L10
      Scenario: Negative Test - Incorrect phone number format on Staples
        Given I navigate to the Staples account creation page
        When I enter a unique email
        And I enter "Alexander" into the First Name field of staples
        And I enter "Steve" into the Last Name field of staples
        And I enter "1111111111" into the Phone Number field
        Then I should see a incorrect phone number format error message



