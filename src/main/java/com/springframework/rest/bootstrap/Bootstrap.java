package com.springframework.rest.bootstrap;

import com.springframework.rest.domain.Category;
import com.springframework.rest.domain.Customer;
import com.springframework.rest.domain.Vendor;
import com.springframework.rest.repository.CategoryRepository;
import com.springframework.rest.repository.CustomerRepository;
import com.springframework.rest.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomer();
        loadVendors();
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Vendor 3");

        Vendor vendor4 = new Vendor();
        vendor4.setName("Vendor 4");

        Vendor vendor5 = new Vendor();
        vendor5.setName("Vendor 5");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
        vendorRepository.save(vendor4);
        vendorRepository.save(vendor5);
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
