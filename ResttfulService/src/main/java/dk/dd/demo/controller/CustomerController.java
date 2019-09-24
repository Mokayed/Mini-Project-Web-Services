package dk.dd.demo.controller;

import dk.dd.demo.model.Customer;
import dk.dd.demo.repo.CustomerRepo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

 @Autowired
  private JdbcTemplate jdp;
 @Autowired
 private CustomerRepo cutomerRepo;

 @GetMapping("/all")
    public List<Customer> getAllCustomers(){
     return (List<Customer>) cutomerRepo.findAll();
 }
    @GetMapping("/{name}")
    public Customer getCustomer(@PathVariable String name){
        return (Customer) cutomerRepo.findByName(name);
    }

    @GetMapping("/getBalance/{name}")
    public String getBalance(@PathVariable String name) throws JSONException {

     Customer c = cutomerRepo.findByName(name);
     JSONObject json = new JSONObject();
     json.put("balance",c.getAccount().getBalance());
     return json.toString();
    }

    @GetMapping("/deposit/{name}/{deposit}")
    public String deposit(@PathVariable String name, @PathVariable long deposit) throws JSONException {

        Customer c = cutomerRepo.findByName(name);
        c.getAccount().setBalance(c.getAccount().getBalance()+deposit);
        jdp.update("update account " + " set balance = ? " + " where id = ?",
                new Object[] { c.getAccount().getBalance(), c.getAccount().getId() });
        JSONObject json = new JSONObject();
        json.put("balance",c.getAccount().getBalance());
        return json.toString();

    }

    @GetMapping("/withdraw/{name}/{withdraw}")
    public String withdraw(@PathVariable String name, @PathVariable long withdraw) throws JSONException {
        JSONObject json = new JSONObject();
        Customer c = cutomerRepo.findByName(name);
        if(withdraw <= c.getAccount().getBalance()){
            c.getAccount().setBalance(c.getAccount().getBalance()-withdraw);
            jdp.update("update account " + " set balance = ? " + " where id = ?",
                    new Object[] { c.getAccount().getBalance(), c.getAccount().getId() });

            json.put("message","withdraw is completed your balance now is: "+ c.getAccount().getBalance());
            return json.toString();
        }
        else {
            json.put("message","withdraw has failed, your balance is: "+ c.getAccount().getBalance());
            return  json.toString();
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id){

        Optional<Customer> found = cutomerRepo.findById(id);
        if(!found.isPresent())
            return ResponseEntity.badRequest()
                    .header("Customer.Header", "no student found")
                    .body("Try again, no student found with id:"+ id);
        cutomerRepo.deleteById(id);
        return  ResponseEntity.ok()
                .header("Customer-Header", "student with id :"+id+"is been deleted")
                .body("student with id :"+id+" "+"is been deleted");
    }

    @PutMapping("/update/{id}")
public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable int id){
        Optional<Customer> found = cutomerRepo.findById(id);
        if(!found.isPresent())
            return ResponseEntity.badRequest()
            .header("Customer.Header", "fee")
            .body("Try again");

        customer.setId(id);
        cutomerRepo.save(customer);
        return  ResponseEntity.ok()
                .header("Customer-Header", "fee")
                .body("Custom header set");

    }
}
