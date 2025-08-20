Feature: Searching on BestBuy

  Scenario: searching for a product
    Given I am on the BestBuy home page
    When When I close the add modal and search for “macbook pro”
    Then one of the laptops listed should be 13.3 8GB Memory and 256GB SSD

    @T2
  Scenario:
    Given I perform the above search
    When I click the “Add to Cart” button next to the laptop
    Then I should see a modal window with the cart subtotal


    @T2
  Scenario:
    Given I am on the Best Buy modal page
    When I click on go to cart
    Then I navigate to the laptop and the order summary

  @P2
  Scenario:  Remove item from cart
    Given I am on the Best Buy Cart page
    When I remove an item from the cart
    Then I verify that the item is removed from the cart
    
  Scenario: Creating a new user
    Given  I am on the BestBuy home page
    Then I click on Account button
    And  I see the panel with Create account button
    When I click on the Create Account button
    Then I should be navigated to Create Account page
    And I can enter all of my account information

