package com.springframework.rest.bootstrap;

import com.springframework.rest.domain.Category;
import com.springframework.rest.domain.Customer;
import com.springframework.rest.repository.CategoryRepository;
import com.springframework.rest.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        loadCategories();

        loadCustomer();
    }

    private void loadCustomer() {
        Customer customer1 = new Customer();
        customer1.setFirstname("Andrew");
        customer1.setLastname("Garfield");

        Customer customer2 = new Customer();
        customer2.setFirstname("James");
        customer2.setLastname("Bedrow");

        Customer customer3 = new Customer();
        customer3.setFirstname("Alex");
        customer3.setLastname("Davids");

        Customer customer4 = new Customer();
        customer4.setFirstname("Rhye");
        customer4.setLastname("Gacillos");

        Customer customer5 = new Customer();
        customer5.setFirstname("Leslie");
        customer5.setLastname("Grass");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);

        System.out.println("Customer count: " + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded: " + categoryRepository.count());
    }
}
