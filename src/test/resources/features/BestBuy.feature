Feature: Searching on BestBuy

  @T1 #robert
  Scenario: searching for a product
    Given I am on the BestBuy home page
    When When I close the add modal and search for “macbook pro”
    Then one of the laptops listed should be 14 24GB Memory and 512GB SSD

  @T1 #robert
  Scenario: adding product to cart
    Given I perform the above search
    When I click the “Add to Cart” button next to the laptop
    Then I should see a modal window with the cart subtotal

  @T1 #Tata
  Scenario: checking product is in the cart page
    Given I am on the Best Buy modal page
    When I click on go to cart
    Then I navigate to the laptop and the order summary

  @T1 #Tata
  Scenario:  Remove item from cart
    Given I am on the Best Buy Cart page
    When I remove an item from the cart
    Then I verify that the item is removed from the cart

  Scenario: Clean up
    Then I close the browser