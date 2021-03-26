package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.User;
import com.kinga.onlineshop.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    private UserManager userManager;

    @Autowired
    public UserApi(UserManager userManager) {
        this.userManager = userManager;
    }




}
