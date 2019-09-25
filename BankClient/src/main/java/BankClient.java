import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;

public class BankClient {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("hello welcome to the SOAP/RESTFUL client for a bank business! type 1 to use SOAP, type 2 to use RESTful");


            String answer = scanner.next();
            if (answer.equals("1")) {
                System.out.println("you have chosen SOAP, here are the instructions:\n" +
                        "type 1 to see all customers\n" +
                        "type 2 to get a balance from a customer\n" +
                        "type 3 to deposit to aCustomerDetailsRequest customer account\n" +
                        "type 4 to withdraw from a customer account\n" +
                        "type back for main menu");

                while (true) {
                    String soapAnswer = scanner.next();
                    if (soapAnswer.equals("1")) {
                        String url = "http://localhost:8085/service/customerDetailsWsdl.wsdl";
                        String soapAction = "";
                        callSoapWebService(url, soapAction, 1, scanner);
                    }
                    if (soapAnswer.equals("2")) {
                        String url = "http://localhost:8085/service/customerDetailsWsdl.wsdl";
                        String soapAction = "";
                        callSoapWebService(url, soapAction, 2, scanner);

                    }
                    if (soapAnswer.equals("3")) {
                        String url = "http://localhost:8085/service/customerDetailsWsdl.wsdl";
                        String soapAction = "";
                        callSoapWebService(url, soapAction, 3, scanner);

                    }
                    if (soapAnswer.equals("4")) {

                        String url = "http://localhost:8085/service/customerDetailsWsdl.wsdl";
                        String soapAction = "";
                        callSoapWebService(url, soapAction, 4, scanner);
                    }
                    if (soapAnswer.equals("back")) {


                    }
                }


            }
            if (answer.equals("2")) {

                System.out.println("you have chosen RESTful, here are the instructions:\n" +
                        "type 1 to see all customers\n" +
                        "type 2 to get a balance from a customer\n" +
                        "type 3 to deposit to aCustomerDetailsRequest customer account\n" +
                        "type 4 to withdraw from a customer account\n" +
                        "type back for main menu");
                while (true) {
                    String restAnswer = scanner.next();
                    if (restAnswer.equals("1")) {
                        final String uri = "http://localhost:8080/customer/all";

                        RestTemplate restTemplate = new RestTemplate();
                        String result = restTemplate.getForObject(uri, String.class);

                        System.out.println(result);
                    }
                    if (restAnswer.equals("2")) {
                        System.out.println("please type your first name");
                        String name = scanner.next();
                        final String uri = "http://localhost:8080/customer/getBalance/" + name;

                        RestTemplate restTemplate = new RestTemplate();
                        String result = restTemplate.getForObject(uri, String.class);

                        System.out.println(result);

                    }
                    if (restAnswer.equals("3")) {
                        System.out.println("please type your first name");
                        String name = scanner.next();
                        System.out.println("please type an amount to deposit into account");
                        String amount = scanner.next();
                        final String uri = "http://localhost:8080/customer/deposit/" + name + "/" + amount;

                        RestTemplate restTemplate = new RestTemplate();
                        String result = restTemplate.getForObject(uri, String.class);

                        System.out.println(result);

                    }
                    if (restAnswer.equals("4")) {

                        System.out.println("please type your first name");
                        String name = scanner.next();
                        System.out.println("please type an amount to withdraw from account");
                        String amount = scanner.next();
                        final String uri = "http://localhost:8080/customer/withdraw/" + name + "/" + amount;

                        RestTemplate restTemplate = new RestTemplate();
                        String result = restTemplate.getForObject(uri, String.class);

                        System.out.println(result);
                    }
                    if (restAnswer.equals("back")) {
                        break;
                    }
                    System.out.println("please choose one of the 5 options again if you wish.");
                }
            }

        }
    }

    private static void getCustomerByName(SOAPMessage soapMessage, String name) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "bank";
        String myNamespaceURI = "bank";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("CustomerDetailsRequest", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("firstName", myNamespace);
        soapBodyElem1.addTextNode(name);
    }

    private static void getBalance(SOAPMessage soapMessage, String name) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "bank";
        String myNamespaceURI = "bank";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);


        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("BalanceRequest", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("firstName", myNamespace);
        soapBodyElem1.addTextNode(name);
    }

    private static void deposit(SOAPMessage soapMessage, String name, Long balance) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "bank";
        String myNamespaceURI = "bank";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("DepositRequest", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("firstName", myNamespace);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("amount", myNamespace);
        soapBodyElem1.addTextNode(name);
        soapBodyElem2.addTextNode("" + balance);
    }

    private static void withdraw(SOAPMessage soapMessage, String name, Long balance) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "bank";
        String myNamespaceURI = "bank";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);


        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("WithdrawRequest", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("firstName", myNamespace);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("amount", myNamespace);
        soapBodyElem1.addTextNode(name);
        soapBodyElem2.addTextNode("" + balance);
    }

    private static void callSoapWebService(String soapEndpointUrl, String soapAction, int option, Scanner scanner) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, option, scanner), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction, int option, Scanner scanner) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        if (option == 1) {
            System.out.println("type username");
            String uName = scanner.next();
            getCustomerByName(soapMessage, uName);
        }
        if (option == 2) {
            System.out.println("type username");
            String uName = scanner.next();
            getBalance(soapMessage, uName);
        }
        if (option == 3) {
            System.out.println("type username");
            String uName = scanner.next();
            System.out.println("type amount");
            Long amount = Long.parseLong(scanner.next());
            deposit(soapMessage, uName, amount);
        }
        if (option == 4) {
            System.out.println("type username");
            String uName = scanner.next();
            System.out.println("type amount");
            Long amount = Long.parseLong(scanner.next());
            withdraw(soapMessage, uName, amount);
        }


        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

}
