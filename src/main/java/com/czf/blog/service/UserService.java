package com.czf.blog.service;

import com.czf.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
