# Rest API Automation 

Rest API Automation is a Hybrid automation Framework developed using JAVA and RestAssured, which is a java library that handle rest API request , also asserts response data. BDD Testign approach is followed for this automation framework which is achieved using RestAssured methods given(),when(),then().For designing this project Page Object Model design technique is used where Utilities class has all common methods needed in other parts of script. Maven is used as Build tool to maintain project files, for handling dependencies at one place in POM.xml file , for running tests.As Unit Test framework TestNG is used to create runner files which are used to run the code.Also it is used to assert reponse of the api calls and to prioritise test cases.For reporting detailed report of build run , Allure Reports tool is used.Github is used as source code management tool to keep project source code , also for version controlling.Jenkins is used as Test Execution tool to run automated test cases. 

# Introduction to UI Automation Framework 
Please refer below diagram for brief information regarding API Automation Framework.

<img width="481" alt="Screenshot 2023-09-25 at 12 26 32 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/5a826082-e32e-4eb3-bb59-63cb893a4a9c">

# Prerequisites 
- Install Java
- Install Maven
- Download Allure Reports
- Eclipse IDE 

# Project Run
- Clone repository https://github.com/sanjiwanis2/RestAPIAutomation in your local system.
- Open cloned repository folder on local and open termianl/command prompt for this folder path.

  ## Command to Run automation script 
  ```
  mvn clean test -Dsuitefilename="./suites/${testrunfile}" 
  ```
  Put test runner file name in place of ${testrunfile} in command. For ex. mvn clean test -Dsuitefilename="./suites/alltests.xml"
  
  ## Command to generate Allure Reports
  ```
  allure serve
  ```

# Jenkins Integration

- Freestyle Pipeline job is created on Jenkins local host sevrer which is configured to clone source code from github repository , parameterised to choose which runner file to execute , generate allure report after build run. Currenly Job is triggered manually using Build with Parameters option.
- Following are some images of Jenkins Job Pipeline View and Configuration
- Pipeline JOB :
    <img width="1010" alt="Screenshot 2023-09-25 at 10 22 00 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/6c888567-3141-47c2-b29e-c64e362ab8dd">
- Job Configuration :
    <img width="1425" alt="Screenshot 2023-09-25 at 10 26 19 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/c3e41ba6-43db-466e-a81f-1ec7da5d9215">
    <img width="1244" alt="Screenshot 2023-09-25 at 10 26 36 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/5b3129e2-95db-4aaa-9b3b-bb8aafe1789f">
    <img width="735" alt="Screenshot 2023-09-25 at 10 27 01 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/8935752d-e405-4df0-9196-ac3045237e74">
    <img width="735" alt="Screenshot 2023-09-25 at 10 27 01 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/41b74c89-9d64-47df-9f4a-06fd053db44e">
    <img width="1237" alt="Screenshot 2023-09-25 at 10 27 18 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/cfc91a5d-e5ad-4126-aeab-afd0fb82fc59">
- Job Trigger :
    <img width="1229" alt="Screenshot 2023-09-25 at 11 43 12 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/cc17fccd-f0e1-4cfd-bea4-19ff1df1fce3">
    <img width="1218" alt="Screenshot 2023-09-25 at 11 43 05 AM" src="https://github.com/sanjiwanis2/RestAPIAutomationSanjiwani/assets/112940142/e217e444-9704-4e6f-8509-59874723379d">

# Advantages of Framework 
- Open Source and free to use tools
- Setup is easy and straight forward
- Easily Integrated with testing framework
- Code is more readable as it used BDD approach.

# Disadvantages of framework 
- Requires good java programming language knowledge
- No inbuilt reporting mechanism
- It doesn't support testing of SOAP APIs

# Future Enhancements 
- Integrate with Docker
- Jenkins build trigger when code is pushed on github main branch




    






  
