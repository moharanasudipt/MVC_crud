package com.example.mvc_crud;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CrudTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createTest(){           //for inserting
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
    public void retriveAll(){           //for retrive the data
        Iterable<User> user=  userRepository.findAll();    //findAll() is used to retrived data from the database
        Assertions.assertThat(user).isNotNull();
        for (User user1:user){
            System.out.println(user1);
        }
    }

    @Test
    public void updateTest(){
        int userId=1;
        Optional<User> user =userRepository.findById(userId);
        User optionalUser=user.get();
        optionalUser.setPassword("BUG");
        userRepository.save(optionalUser);
        User saveUser=userRepository.findById(userId).get();
        Assertions.assertThat(saveUser.getPassword()).isEqualTo("BUG");
    }
    @Test
    public void retriveById(){
        int userId=5;
        Optional<User> user =userRepository.findById(userId);
        User optionalUser=user.get();
        userRepository.save(optionalUser);
        Assertions.assertThat(optionalUser).isNotNull();
        if (optionalUser!=null){
            System.out.println(optionalUser);
        }
    }
    @Test
    public void deleteById(){
        int userid=4;
        Optional<User> user= userRepository.findById(userid);
        if (!user.isEmpty()){
            userRepository.deleteById(userid);
            Optional<User> user1= userRepository.findById(userid);
            Assertions.assertThat(user1).isNotPresent();
        }else {
            Optional<User> user1 = userRepository.findById(userid);
            Assertions.assertThat(user1).isPresent();
        }
    }
}