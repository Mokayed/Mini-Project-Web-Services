# Mini-Project-Web-Services


<h1>System Integration Assignment: Mini Project: Web Services</h1>

<h5>authors are: Hallur við Neyst, Murched Kayed, hazems</h5>

<h1>summary of application development and implementation</h1>

<p>
First we decided on our business value. We chose a customer/account service as a business value for a bank
because that banks need to easily give their customers services such as withdraw, deposit and so on. 

We created two diffirent services, we started developing RESTful service: 
We made entities for the customer and account. The data of the entities are saved in a h2 database.
The endpoints of the RESTful service have the operations of: see all customers, see customer by name, deposit to account,
withdraw from account.

SOAP service:
The soap service contains an "xsd" file which defines methods and objects that are needed in the WSLD url. 
With the use of dependencies such as WSDL4j and jaxb2 we could autogenerate the classes and methods from the xsd file.
The data in the service is saved in a hashmap. Our endpoints have four operations: see sustomer, see balance of customer-account, deposit to account, withdraw from account.
</p>

<h1>installation instructions</h1>
<ul>
  <li>clone the repository</li>
  <li>the repository contains 3 diffirent projects: a client, a SOAP service and a RESTful service</li>
  <li>open the 3 projects in a java IDE (we used intellij)</li>
  <li>start the RESTful service by clicking run (the RESTservice port is 8080)</li>
  <li>you can check the h2 data by going to http://localhost:8080/h2-console and use jdbc:h2:mem:testdb as jdbc url</li>
  <li>an example of one of our REST endpoints: http://localhost:8080/customer/all (all customers)</li>
  <li>start the SOAP service by running the BankingServiceApplication.java file.</li>
  <li>check if the service is up and running by going to: http://localhost:8085/service/customerDetailsWsdl.wsdl</li>
  <li>if by any chance that you did NOT get it up and running, chances are that you will need to downgrade your jdk to 1.8</li>
  <li>start the client by going to BankClient.java and run the file. You will be greeted by a scanner message</li>
  <li>either choose SOAP or RESTful as a service, and after choose the operation you wish to use (see customer, see balance of customer, deposit to account, withdraw from account)</li>
</ul>


