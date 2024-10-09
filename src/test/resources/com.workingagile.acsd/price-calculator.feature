Feature: Price calculation

  The price calculation for Working-Agile is quite complex.
  As a general rule, the sooner you buy your seat, the cheaper the price.

  Rule: Early Bird with proportional discount 10 days before the training course

  The last 10 day before the training course have a proportional discount rule.
  With each day passing the price increases.


    Scenario: Proportional discount on day 6

    An example of the proportional discount, in the middle of the interval of the 10
    days leading towards the training course.

      And the following training course has been scheduled:
        | training course | full price | scheduled date | current date |
        | CSD             |  4000      | 30/09/2024     | 30/09/2024   |
      When a client checks for the current price
      Then the discounted price should be 3820

