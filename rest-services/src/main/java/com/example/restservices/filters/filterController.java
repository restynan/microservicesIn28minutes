package com.example.restservices.filters;

import com.example.restservices.models.Customer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class filterController {
//display only userName and salary
    @GetMapping("/customer")
    public MappingJacksonValue retrieveCustomer(){
        Customer cust= new  Customer ("Dan","123",40000);

        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("userName","salary");

        FilterProvider filters= new SimpleFilterProvider().addFilter("customerFilter",filter );

        MappingJacksonValue mapping = new MappingJacksonValue(cust);

        mapping.setFilters(filters);

        return mapping;


    }
    @GetMapping("/customers")
    public MappingJacksonValue retrieveCustomers(){
        List<Customer>list= new ArrayList<>(Arrays.asList(new  Customer ("Dan","123",40000),
                new  Customer ("resty","123",70000)));

        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("userName","password");

        FilterProvider filters= new SimpleFilterProvider().addFilter("customerFilter",filter );

        MappingJacksonValue mapping = new MappingJacksonValue(list);

        mapping.setFilters(filters);

        return mapping;



    }





}
