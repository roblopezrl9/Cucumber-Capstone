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
