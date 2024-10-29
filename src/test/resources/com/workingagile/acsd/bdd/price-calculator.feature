Feature: Price calculation

  The price calculation for Working-Agile is quite complex.
  As a general rule, the sooner you buy your seat, the cheaper the price.

  Rule: Early Bird with proportional discount up to 10 days before the training course

  The last 10 day before the training course have a proportional discount rule.
  With each day passing the price increases.

    Scenario: First day of the proportional discount of a CSD training course

    The proportional discount starts at day 10 before the scheduled date of the
    training course. For each day a constant discount of R$ 30,00 is added.

      Given the following training course has been scheduled:
        | training course | full price | scheduled date | current date |
        | CSD             |  4000      | 30/09/2024     | 20/09/2024   |
      When a client checks for the current price
      Then the discounted price should be 3700

    Scenario: First day of the proportional discount of a CSM training course

    The proportional discount starts at day 10 before the scheduled date of the
    training course. For each day a constant discount of R$ 20,00 is added.

      Given the following training course has been scheduled:
        | training course | full price | scheduled date | current date |
        | CSM             |  4000      | 30/09/2024     | 20/09/2024   |
      When a client checks for the current price
      Then the discounted price should be 3800

    Scenario: First day of the proportional discount of a CSPO training course

    The proportional discount starts at day 10 before the scheduled date of the
    training course. For each day a constant discount of R$ 20,00 is added.

      Given the following training course has been scheduled:
        | training course | full price | scheduled date | current date |
        | CSPO            |  4000      | 30/09/2024     | 20/09/2024   |
      When a client checks for the current price
      Then the discounted price should be 3800

    Scenario: Last day of the proportional discount

    The last day of the early bird discount is 2 days before the training course.

      Given the following training course has been scheduled:
        | training course | full price | scheduled date | current date |
        | CSPO            |  4000      | 30/09/2024     | 28/09/2024   |
      When a client checks for the current price
      Then the discounted price should be 3960

    Scenario: Proportional discount on day 6

    An example of the proportional discount, in the middle of the interval of the 10
    days leading towards the training course.

      Given the following training course has been scheduled:
        | training course | full price | scheduled date | current date |
        | CSD             |  4000      | 30/09/2024     | 24/09/2024   |
      When a client checks for the current price
      Then the discounted price should be 3820

    Scenario: Last day of the proportional discount

    The last day of the early bird discount is 2 days before the training course.

      Given the following training course has been scheduled:
        | training course | full price | scheduled date | current date |
        | CSPO            |  4000      | 30/09/2024     | 28/09/2024   |
      When a client checks for the current price
      Then the discounted price should be 3960

#----------------------------------------------------------------------------------------------

    Scenario Template: Early bird discounts

    Early bird discounts apply a proportional discount, each day. The value depends on the
    training course.
    This discount starts 10 days prior to the scheduled training date.

      Given the following training course has been scheduled:
        | training course   | full price   | scheduled date   | current date    |
        | <training course> | <full price> | <scheduled date> | <current date>  |
      When a client checks for the current price
      Then the discounted price should be <discounted price>

      Examples: CSPO discounted with R$ 20 per day
        | training course |  scheduled date | current date | full price | discounted price | days | why              |
        | CSPO            |  30/09/2024     | 29/09/2024   | 4000       | 4000             | 1    | full price       |
        | CSPO            |  30/09/2024     | 28/09/2024   | 4000       | 3960             | 2    | early bird       |
        | CSPO            |  30/09/2024     | 24/09/2024   | 4000       | 3880             | 6    | early bird       |
        | CSPO            |  30/09/2024     | 20/09/2024   | 4000       | 3800             | 10   | early bird       |
        | CSPO            |  30/09/2024     | 19/09/2024   | 4000       | 3600             | 11   | super early bird |

      Examples: CSM discounted with R$ 20 per day

        | training course |  scheduled date | current date | full price | discounted price | days | why              |
        | CSM             |  30/09/2024     | 29/09/2024   | 4000       | 4000             | 1    | full price       |
        | CSM             |  30/09/2024     | 28/09/2024   | 4000       | 3960             | 2    | early bird       |
        | CSM             |  30/09/2024     | 24/09/2024   | 4000       | 3880             | 6    | early bird       |
        | CSM             |  30/09/2024     | 20/09/2024   | 4000       | 3800             | 10   | early bird       |
        | CSM             |  30/09/2024     | 19/09/2024   | 4000       | 3500             | 11   | super early bird |

      Examples: CSD discounted with R$ 30 per day
        | training course |  scheduled date | current date | full price | discounted price | days | why              |
        | CSD             |  30/09/2024     | 29/09/2024   | 4000       | 4000             | 1    | full price       |
        | CSD             |  30/09/2024     | 28/09/2024   | 4000       | 3940             | 2    | early bird       |
        | CSD             |  30/09/2024     | 24/09/2024   | 4000       | 3820             | 6    | early bird       |
        | CSD             |  30/09/2024     | 20/09/2024   | 4000       | 3700             | 10   | early bird       |
        | CSD             |  30/09/2024     | 19/09/2024   | 4000       | 3600             | 11   | super early bird |









