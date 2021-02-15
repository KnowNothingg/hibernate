package com.antra.application;

import com.antra.bean.Company;
import com.antra.bean.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    static EntityManagerFactory EMF =  Persistence.createEntityManagerFactory("hibernatePractice");

    public static void main(String[] args) {
        Company com = new Company("Antra", "21355 Ridgetop Circle, Suite #300 Dulles, VA 20166");

        Employee staff1 = new Employee("John", "Wick");
        Employee staff2 = new Employee("Stop", "Shop");
        Employee staff3 = new Employee("Hello", "World");
        staff1.setCompany(com);
        staff2.setCompany(com);
        staff3.setCompany(com);
//
//        EntityManager em = EMF.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(com);
//        em.persist(staff1);
//        em.persist(staff2);
//        em.persist(staff3);
//
//        em.getTransaction().commit();
//        em.close();

//        getCompany(1);
        getCompany(1);
        EMF.close();

//        saveCompany(com);
//        saveEmployee(staff1);
//        saveEmployee(staff2);
//        saveEmployee(staff3);
    }

    private static void saveCompany(Company com) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();

        em.persist(com);
        em.getTransaction().commit();
        em.close();
    }

    private static void saveEmployee(Employee staff) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();


        em.persist(staff);
        em.getTransaction().commit();
        em.close();
    }

    public static Company getCompany(long id) {
        EntityManager em = EMF.createEntityManager();
        Company com = em.find(Company.class, id);
        Query query = em.createNativeQuery("Select * from Employee e where e.company_id = :id", Employee.class)
                .setParameter("id", id);
        List<Employee> list = query.getResultList();
        for(Employee e : list) {
            System.out.println(e.toString());
        }
//        System.out.println(com.toString());

        em.close();
        return com;
    }

    public static void getEmployee(long id) {
        EntityManager em = EMF.createEntityManager();
        Employee staff = em.find(Employee.class, id);

        System.out.println(staff.toString());
        em.close();
    }
}
