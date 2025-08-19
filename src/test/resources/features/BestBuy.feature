Feature: Searching on BestBuy
@P21
  Scenario: searching for a product
    Given I am on the BestBuy home page
    When When I close the add modal and search for “macbook pro”
    Then one of the laptops listed should be 13.3 8GB Memory and 256GB SSD
