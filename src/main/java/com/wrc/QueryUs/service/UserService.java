package com.wrc.QueryUs.service;

import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserDto> createUser(User user){
        user.setReputation(0);
        userRepository.save(user);
        return Optional.of(entityToDto(user));

    }
    public User findUserById(int id){
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    public User updateUser(User user, int id){
        User u = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return userRepository.save(u);

    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);

    }
    private UserDto entityToDto(User user){
        UserDto u = new UserDto();
        u.setId(user.getId());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setReputation(user.getReputation());
        return u;
    }
}
