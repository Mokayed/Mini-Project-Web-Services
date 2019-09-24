import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(true){
        System.out.println("hello welcome to the SOAP/RESTFUL client for a bank business! type 1 to use SOAP, type 2 to use RESTful");

        String answer = scanner.next();

        if(answer.equals("2")){

            System.out.println("you have chosen RESTful, here are the instructions:\n" +
                    "type 1 to see all customers\n" +
                    "type 2 to get a balance from a customer\n" +
                    "type 3 to deposit to a customer account\n" +
                    "type 4 to withdraw from a customer account\n" +
                    "type back for main menu");
            while(true){
            String restAnswer = scanner.next();
            if(restAnswer.equals("1")){
                final String uri = "http://localhost:8080/customer/all";

                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);

                System.out.println(result);
            }
            if(restAnswer.equals("2")){
                System.out.println("please type your first name");
                String name = scanner.next();
                final String uri = "http://localhost:8080/customer/getBalance/" + name;

                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);

                System.out.println(result);

            }
            if(restAnswer.equals("3")){
                System.out.println("please type your first name");
                String name = scanner.next();
                System.out.println("please type an amount to deposit into account");
                String amount = scanner.next();
                final String uri = "http://localhost:8080/customer/deposit/" + name + "/" + amount;

                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);

                System.out.println(result);

            }
            if(restAnswer.equals("4")){

                System.out.println("please type your first name");
                String name = scanner.next();
                System.out.println("please type an amount to withdraw from account");
                String amount = scanner.next();
                final String uri = "http://localhost:8080/customer/withdraw/" + name + "/" + amount;

                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);

                System.out.println(result);
            }
            if(restAnswer.equals("back")){
                break;
            }
                System.out.println("please choose one of the 5 options again if you wish.");
            }
        }
    }
    }
}
