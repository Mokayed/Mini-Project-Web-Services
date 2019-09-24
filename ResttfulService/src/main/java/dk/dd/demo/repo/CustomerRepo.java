package dk.dd.demo.repo;

import dk.dd.demo.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
   Customer findByName(String name);
}
