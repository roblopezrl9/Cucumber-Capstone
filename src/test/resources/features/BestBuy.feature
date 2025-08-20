Feature: Searching on BestBuy
  @T1
  Scenario: searching for a product
    Given I am on the BestBuy home page
    When When I close the add modal and search for “macbook pro”
    Then one of the laptops listed should be 13.3 8GB Memory and 256GB SSD

    @T2
  Scenario: adding product to cart
    Given I perform the above search
    When I click the “Add to Cart” button next to the laptop
    Then I should see a modal window with the cart subtotal
    
    @T2
  Scenario: checking product is in the cart page
    Given I am on the Best Buy modal page
    When I click on go to cart
    Then I navigate to the laptop and the order summary

  @P2
  Scenario:  Remove item from cart
    Given I am on the Best Buy Cart page
    When I remove an item from the cart
    Then I verify that the item is removed from the cart
    

