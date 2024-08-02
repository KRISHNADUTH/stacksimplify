package com.stacksimplify.restservices.model;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users") // If we want to use table name which is different from entity class name the use this name parameter inside table, there is also a attribute named schema where we can mention the name of the schema in which table needs to be created this will be helpful if table with same name present in two different schema.
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="USER_NAME", length = 50, nullable = false,unique = true)
    private String userName;
    
    @Column(name="FIRST_NAME", length = 50, nullable = false)
    private String firstName;
    
    @Column(name="LAST_NAME", length = 50, nullable = false)
    private String lastName;
    
    @Column(name="EMAIL_ADDRESS", length = 50, nullable = false)
    private String email;
    
    @Column(name="ROLE", length = 50, nullable = false)
    private String role;
    
    @Column(name="SSN",length = 50, nullable = false, unique = true)
    private String ssn;

}
