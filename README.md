# SWAG_Lab Web Automation With Playwright-Java

This repository contains an automated UI test suite for **SWAG_Lab**, built using:

- Java + Playwright
- JUnit 5/JUnit Jupiter as a test framework
- Maven for build and dependency management
- Allure for test reporting

---

## 📁 Folder Structure

    
 

    SWAG_LABS-With-Playwright-Java/
    ├── pom.xml # Maven configuration and dependencies  
    ├── logs/ # Contains detailed execution logs after test run
    │ └── test-execution.log
    ├── src/  
    │ ├── main/    
    │ └── test/  
    │ │ └── java/
    │ │ │  └── configuration/
    │ │ │  │  └── BasePage.java
    │ │ │  │  └── BaseTest.java 
    │ │ │  └── pages/
    │ │ │  │  └── LoginPage.java
    │ │ │  └── test_Cases/
    │ │ │  │  └── LoginPageTestCase.java
    │ │ │  └── utilities/
    │ │ │  │  └── Config.java 
    │ │ └── resources/
           └── logback.xml


    
