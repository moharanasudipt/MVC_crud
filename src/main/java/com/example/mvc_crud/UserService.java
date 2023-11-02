package com.example.mvc_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundexception {
        Optional<User> Result=userRepository.findById(id);
        if(Result.isPresent())
        {
            return Result.get();
        }else  throw new UserNotFoundexception("User is not found with ID: "+id);

    }
}
