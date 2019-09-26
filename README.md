
<h1 align="center">System Integration Assignment: Mini Project: Web Services</h1>
<h6>Authors: Hallur við Neyst, Murched Kayed, Hazem</h6>

<h1>Description <g-emoji class="g-emoji" alias="page_with_curl" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4c3.png">📃</g-emoji>
</h1>


<h4>Business value</h4>

<p>
  We choose a customer/account services as a business value for a bank
because that banks need to easily give their customers services such as withdraw, deposit and so on. 
</p>

<h4>Development process</h4>

<p>
We created two diffirent services that handel the customer and account details, both of the services contains: String Name,String adresse, Object account(id, balance).
  Then we created a client to consume the data from both of the services.
</p>  

<p align="center">
  
<img src="https://github.com/Mokayed/Mini-Project-Web-Services/blob/master/BANKMINILAST.PNG" alt="UML"  height="auto" width="auto">

</p>  
  
<h4> RESTful service: </h4>
<p>
We made entities for the customer and account. The data of the entities are saved in a h2 database.
The endpoints of the RESTful service have the operations of: 
</p>

<ul>
  <li>1-See all customers,</li>
  <li>2-See customer by name</li>
  <li>3-Deposit to account</li>
  <li>4-Withdraw from account</li>
</ul>

<h4>SOAP service:</h4>

<p>
The soap service contains an "xsd" file which defines methods and objects that are needed in the WSLD url. 
With the use of dependencies such as WSDL4j and jaxb2 we could autogenerate the classes and methods from the xsd file.
The data in the service is saved in a hashmap. Our endpoints have four operations: 
</p>

<ul>
  <li>1-see customer</li>
  <li>2-see balance of customer-account</li>
  <li>3-deposit to account</li>
  <li>4-withdraw from account.</li>
</ul>

<h1>Setup<g-emoji class="g-emoji" alias="gear" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/2699.png">⚙️</g-emoji></h1>
<p>Clone the repository, The repository contains 3 diffirent projects: a client, a SOAP service and a RESTful service, open each of them them in a java IDE (we used intellij), now follow those steps:</p>

<h4>1-Strart the RESTful serivce</h4>

  <ul>
  <li>1-Start the RESTful service by clicking run (the RESTservice port is 8080)</li>
  <li>2-check the h2 database by going to http://localhost:8080/h2-console and use jdbc:h2:mem:testdb as jdbc url</li>
  <li>3- Tetst the rest endpoint by going to: http://localhost:8080/customer/all (all customers)</li>
  </ul>
  
  <h4>2-Strart the SOAP serivce</h4>
  
  <ul>
  <li>start the SOAP service by running the BankingServiceApplication.java file.</li>
  <li>check if the service is up and running by going to: http://localhost:8085/service/customerDetailsWsdl.wsdl</li>
  <li>If by any chance that you did NOT get it up and running, chances are that you will need to downgrade your jdk to 1.8</li>
  </ul>

  <h4>3-Now strart the Client</h4>
  
  <ul>
  <li>start the client by going to BankClient.java and run the file. You will be greeted by a scanner message</li>
  <li>Now you can choose between SOAP or RESTful as a service to serve you, after choosing the serivce you want choose the operation you wish to use:</li>
   <li>1-see customer</li>
  <li>2-see balance of customer-account</li>
  <li>3-deposit to account</li>
  <li>4-withdraw from account.</li>
</ul>


