
Refactoring exercises of Scrum Alliance A-CSD training course.

### What is this repository for? ###

* Refactoring exercises illustrating Refactoring, Unit testing, Characterization tests, clean coding, dependency injection, single responsibility principle  

### What is this branch for? ###

* Show the refactored application where the Dependency Injection principle has been applied, so that basic characterization tests could be implemented. 

### What should you do here?

* Execute the characterization tests.
* Refactor applying the Single Responsibility Principle (SPR) to separate the business logic from infrastructure code.

### Branches

* 30-price-calculator-database: the initial state of the backend of the application. Integrated with a postgres database.
* 31-price-calculator-database-characterization-test: implemented basic characterization tests.
* 32-price-calculator-refactored: refactored version, separated business logic from infrastructure code.
* 33-price-calculator-with-unit-tests: business rules illustrated by unit tests.


### Business Rules

#### The training courses and prices

* There are 3 types of training courses: CSD, CSM and CSPO
* The price of a training course is defined by the training company.
* Guaranteed minimum price of R$ 900,00 for a CSD, R$ 1000,00 for a CSM and R$ 1200,00 for a CSPO training.

#### Discounts

* The prices for the training courses listed on the website must be updated on a daily basis.
* Discounts are based on how early a training course seat is bought

* **Early Bird**: when buying a seat within 10 days prior the training course, there is a daily discount of R$ 30,00 for a
  CSD training, and R$ 20,00 for the other training courses.
  
* **Super Early Bird**: when buying a seat earlier than 10 days, then there is a fixed discount of R$ 500,00 for a CSM,
  and R$ 400,00 for the other training courses
* No discount applicable the day before the training course
* No discount applicable 5 days prior to the training course whenever there are less than 3 seats left



### Who do I talk to? ###

* Axel Wilhelm Berle
* axelberle@gmail.com


