Feature: Searching on BestBuy



Scenario: Creating a new user
  Given  I am on the BestBuy home page
  Then I click on Account button
  And  I see the panel with Create account button
  When I click on the Create Account button
  Then I should be navigated to Create Account page
  And I can enter all of my account information

