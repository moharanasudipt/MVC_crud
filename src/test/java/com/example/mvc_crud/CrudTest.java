package com.example.mvc_crud;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CrudTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createTest(){
    User newUser=new User();
    newUser.setEmail("susant@gmail.com");
    newUser.setPassword("2004");
    newUser.setFirstName("Susanta");
    newUser.setLastName("Debata");
    userRepository.save(newUser);
        Assertions.assertThat(newUser).isNotNull();
        Assertions.assertThat(newUser.getId()).isGreaterThan(0);
    }
    @Test
    public void retriveAll(){
        Iterable<User> user=  userRepository.findAll();    //findAll() is used to retrived data from the database
        Assertions.assertThat(user).isNotNull();
        for (User user1:user){
            System.out.println(user1);
        }

    }
}