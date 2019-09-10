# Professional Practice Assignment 1

This assignment was completed by Connor McAdorey and Zachary Tsarnas 

## Naming Conventions

For our assignment we used Java in Eclipse and JUnit as a testing framework. Our tests were named in the fashion of "test_WhatisBeingTesting". We utilized the Arrange Act Assert standard to organize our unit tests. Due to the nature of the fucntions we used, it was important to test edge cases in order to acheive complete code coverage. An example of this would be the boundary between "underweight" and "normal weight" in the BMI calculator.

## Setup and Execution
ECLIPSE DOWNLOAD:
https://urldefense.proofpoint.com/v2/url?u=https-3A__www.eclipse.org_downloads_packages_release_kepler_sr1_eclipse-2Dide-2Djava-2Ddevelopers&d=DwIGaQ&c=sJ6xIWYx-zLMB3EPkvcnVg&r=luKm9xdYuv6JR7s8bEMK5S_AjQml5ZdXiZaBfBT2nOc&m=fCl98VmXqq0SZ4EyqlEIkYLAssL1U8o71zw7DJXB13s&s=ICZ-NuLKJvbVHzov_f36Wm7ptW_vJChGNvwOrZ9xM4Q&e= 
JAVA DOWNLOAD:
https://urldefense.proofpoint.com/v2/url?u=https-3A__www.oracle.com_technetwork_Java_javase_downloads_jre8-2Ddownloads-2D2133155.html&d=DwIGaQ&c=sJ6xIWYx-zLMB3EPkvcnVg&r=luKm9xdYuv6JR7s8bEMK5S_AjQml5ZdXiZaBfBT2nOc&m=fCl98VmXqq0SZ4EyqlEIkYLAssL1U8o71zw7DJXB13s&s=lOjK3wiZETDhcpcTqaKOIRGnHr4znl-bn5p_WfM994w&e= 
JUNIT DOWNLOAD:
https://urldefense.proofpoint.com/v2/url?u=https-3A__search.maven.org_search-3Fq-3Dg-3Aorg.junit.jupiter-2520AND-2520v-3A5.5.2&d=DwIGaQ&c=sJ6xIWYx-zLMB3EPkvcnVg&r=luKm9xdYuv6JR7s8bEMK5S_AjQml5ZdXiZaBfBT2nOc&m=fCl98VmXqq0SZ4EyqlEIkYLAssL1U8o71zw7DJXB13s&s=1CkIideAmcDPzMomuWDFxVzuYFs0JDZQwIWkzEnwBvk&e= 

We used Eclipse 4.7.2, Java SE-9 and JUnit Jupiter 5.5.2. Following the documents linked we are able to install eclipse create a new project and a new package called testing. Under a custom package we created a JunitTesting class that houses all of our methods. Becuase we added add Junit tests to the eclipse project we created individual Junit test case classes for each method. Each test case class contains multiple tests for the method. By right clicking on the package, we are able to see the code coverage by scrolling to see Coverage As -> JUnit Test. 

## Screenshot of all tests passing

https://github.com/tsarni21/PPA-Assignment1/blob/master/All%20Tests%20Passing.png

## Test Coverage Screenshot

https://github.com/tsarni21/PPA-Assignment1/blob/master/Code%20Coverage.png

## Full Functionality Screencast

https://github.com/tsarni21/PPA-Assignment1/blob/master/Full%20Functionality%20Screenrecording.mov

## Red, Green, Refractor Screencast

### Written Answers

Describe your unit testing & TDD experience. What do you think of unit testing & TDD? Do you think
it’s useful for a real project? Benefits & Drawbacks to TDD. 


Zachary Tsarnas - In all, the unit testing experience was very different compared to any prior experience I have had. While CEN3031- Intro to Software Engineering, attempted to implement unit tests as part of the curriculum and provide examples, we never had to actual implement them in our final project. It is also for this reason that we felt more comfortable using Java instead of Javascript when creating the overall code and tests. I think unit testing plays an important role and allows us as software engineers an opportunity to break our code. We should always be looking for ways to make our code more efficient or better prepared for the unknown. In regards to TDD, I believe it is not as efficient as BDD. However, this may be just because of the mentality in which I code. Understanding the behavior, and manipulating the code in order to get it do what I want it do is far more appealing when coding to me, then writing tests first. I prefer a trial and error approach. 
I do think that unit testing and TDD can be effective for a real project. In fact, as a student in the Intro to Formal Methods class, I believe test driven development would be perfect there. Mission critical and mission safety software should be prepared to not fail and writing tests/requirements for your code before actual writing it would help with this. However, this would only be if the software engineers for the code were extremely thorough in the test they created. If they don’t create tests that could cover 98%+ and ideally 100%, the software is not ready for deployment. This is a drawback to test-driven development because while it may not require more labor, there is a more intensive thought process that goes into it.


Connor McAdorey - Prior to taking this class I had experience writing unit tests in C# on an enterprise project for Ultimate Software. My experience with those unit tests were more quality assurance and BDD. The business analysts would provide use cases and scenarios that needed to be tested on the exisitng code and i would then write unit tests for the web tool and the desktop application. This project and class are a first look at TDD for me. Personally, I prefer being able to write the functioning code first and then go back and write the tests to break or optimize that code. I think unit testing is very useful and effective and should be applied to most programs to efficently find bugs, defects and faults in code. I understand TDD can be effective in situations where projects are being started from scratch, its easy to lay down a groundowork of tests to start with and then expand upon the code from there.  However, in the case of an already exisitng code base and with specific scenarios in mind BDD might be more useful. 

## Authors

* **Connor McAdorey**
* **Zachary Tsarnas**
