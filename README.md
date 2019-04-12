# Agoda Assignment Setup Guide
This project demonstrates a basic Testng Maven framework project with Surefire Plugin integration. The functionality covered here is validation for password change with following conditions


* At least 18 alphanumeric characters and list of special chars !@#$&amp;*
* At least 1 Upper case, 1 lower case ,least 1 numeric, 1 special character
* No duplicate repeat characters more than 4
* No more than 4 special characters
* 50 % of password should not be a number
* Old password should match with system
* New password should be a valid password
* password is not similar to old password &lt; 80% match.


The Testng test is parameterised with data providers and covers positive and Negative scenarios for password change.

---
### To Get Started

#### Pre-requisites
1.Java installed globally in the system.
https://www.java.com/en/download/

**Note** Min Java version 8.x.x

2.Maven installed globally in the system.
https://maven.apache.org/download.cgi

**Note** Min Maven version 3.3+

3.Text Editor(Optional) installed-->Eclipse/Idea Intellij.

#### Setup & Running Scripts
* Clone the repository into a folder
* Go inside the project root folder and run following command from terminal/command prompt to install all the dependencies from pom.xml

```
mvn install
```

* Then run the following command

```
mvn test
``` 

* Maven surefire plugin would then trigger the execution of test cases from the **testng.xml** present in the project root directory.


#### Surefire-Reports

A report is generated in the **target/surefire-reports** folder. Click open the **index.html** file and **emailable-report.html** file for the test reports

















