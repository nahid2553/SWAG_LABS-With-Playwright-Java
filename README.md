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



## ✅ Features

- Page Object Model (POM) design pattern
- JUnit 5 for test configuration and execution
- Browser-based automation using Playwright
- Configuration via JUnit 5 annotations and properties file
- Allure HTML reports with test evidence

## 🧪 Test Scenarios

The automation test suite includes:

## Class Analysis
1️⃣ Config Class
- Centralized Settings & Control Panel
- Stores all important settings in ONE place

2️⃣ BasePage Class
- Common Toolkit
- Stores common methods that every page needs

3️⃣ LoginPage Class
- Login-Specific Actions
- Extends BasePage & Contains login page selectors and login methods

4️⃣ BaseTest Class
- Initializes browser before each test
- Test Setup & Cleanup after each test

5️⃣ LoginPageTestCase Class
- Contains all login-related test cases with possible scenarios
- Uses LoginPage to perform actions
- Uses assertions to verify results

6️⃣ Logback Configuration (logback.xml)
- Controls where logs go (console, file)
- Controls what gets logged (DEBUG, INFO, ERROR)

7️⃣ POM.xml (Maven Configuration)
- Project Dependencies & Build Settings
