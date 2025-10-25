Feature: Validing the Books api Functionality

@Smoke
Scenario: Validing the book adding functionality
Given Add the "Learn Appium Automation with Java" with isbn "ggd" and aisle "787" and author "kir"
When  User calls "AddBooksAPI" payload
Then  User calls the request to verify status code "200"


Scenario: Validing the Books getting functionality
Given User calls the books with queryparams
When User calls the books with "GetBooksAPI" payload
Then User validates the fields in the response
