package com.bext.manytomanybidirtable.dao;

import com.bext.manytomanybidirtable.entity.Customer;

public interface CustomerDao {
    public Customer getCustomerFindByName(String name);
   // public List<CustomersItems> getAllCustomersItems();
}
