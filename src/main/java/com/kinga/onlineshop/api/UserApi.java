package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.User;
import com.kinga.onlineshop.dto.UserDto;
import com.kinga.onlineshop.manager.UserManager;
import com.kinga.onlineshop.middleware.Hash;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    private UserManager userManager;
    private ModelMapper modelMapper;

    @Autowired
    public UserApi(UserManager userManager, ModelMapper modelMapper) {
        this.userManager = userManager;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UserDto> getUsers(){
        Iterable<User> iterable = userManager.findAll();
        List<User> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public UserDto getById(@PathVariable("id") Long id){
        Optional<User> optional = userManager.findById(id);
        UserDto userDto = convertToDto(optional.get());
        return userDto;
    }

    @PostMapping    //register
    public UserDto addUser(@RequestBody UserDto userDto){
        User user = convertToEntity(userDto);
        List<User> login = userManager.findByLogin(user.getLogin());
        if(!login.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this login already exists");
        }
        List<User> email = userManager.findByEmail(user.getEmail());
        if(!email.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this e-mail already exists");
        }
        String password = Hash.hash(user.getPassword());
        user.setPassword(password);
        user.setRole("user");
        User userCreated = userManager.save(user);
        return convertToDto(userCreated);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        Optional<User> optional = userManager.findById(id);
        User user = optional.get();
        if(userDto.getEmail() != null){
            List<User> email = userManager.findByEmail(userDto.getEmail());
            if(email.isEmpty()) {
                user.setEmail(userDto.getEmail());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this e-mail already exists");
            }
        }
        if(userDto.getPassword() != null){
            String password = Hash.hash(user.getPassword());
            user.setPassword(password);
        }
        User userCreated = userManager.save(user);
        return convertToDto(userCreated);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userManager.deleteById(id);
    }

    private UserDto convertToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User convertToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
