package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.User;
import com.kinga.onlineshop.dto.UserDto;
import com.kinga.onlineshop.manager.UserManager;
import com.kinga.onlineshop.middleware.Hash;
import com.kinga.onlineshop.middleware.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class LoginApi {

    private UserManager userManager;

    @Autowired
    public LoginApi(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody UserDto userDto){
        List<User> users = userManager.findByLogin(userDto.getLogin());
        if(users.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User with this login does not exists");
        }
        User user = users.get(0);
        if(!Hash.verify(user.getPassword(), userDto.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is incorrect");
        }
        String jwt = JWT.createJWT(String.valueOf(user.getIdUser()), user.getLogin(), user.getRole(), 60 * 60 * 1000);

        ResponseCookie cookie = ResponseCookie
                .from("auth", jwt)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60 * 60 * 1000)
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @DeleteMapping()
    public ResponseEntity logOut(){
        ResponseCookie deleteCookie = ResponseCookie
                .from("auth", null)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .build();
    }
}
