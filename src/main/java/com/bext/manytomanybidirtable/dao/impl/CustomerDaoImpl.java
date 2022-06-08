package com.bext.manytomanybidirtable.dao.impl;

import com.bext.manytomanybidirtable.dao.CustomerDao;
import com.bext.manytomanybidirtable.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Customer getCustomerFindByName(String name) {

        //Query qfindByName = entityManager.createQuery("select u from Customer u where u.name = :qname")
        //        .setParameter("qname", name);

        Query qfindByName = entityManager.createNativeQuery("select * from dummy.customer c where c.name = :qname", Customer.class)
                .setParameter("qname", name);

        return (Customer) qfindByName.getSingleResult();

    }

    /*@Override
    public List<CustomersItems> getAllCustomersItems() {
        TypedQuery<CustomersItems> customersItems = entityManager.createNamedQuery("getAllCustomersItems" , CustomersItems.class);
        return customersItems.getResultList();
    }*/
}
