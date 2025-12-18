Feature: Ticket Booking

  @RegressionTest
  Scenario: Ticket Booking Yatra
    Given Soniya login in yatra website
    When she buys a ticket
    Then she receives a ticket confirmation
