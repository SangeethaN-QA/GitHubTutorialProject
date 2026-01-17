Feature: Ticket Booking

  @RegressionTest
  Scenario: Ticket Booking Yatra Sample
    Given Soniya login in yatra website Sample
    When she buys a ticket Sample
    Then she receives a ticket confirmation Sample

@Arun_changes
  Scenario: Ticket Booking Yatra Sample Arun
    Given Soniya login in yatra website Sample Arun
    When she buys a ticket Sample Arun
    And she receives a ticket confirmation Sample Arun
    Then she confirms booking status Done