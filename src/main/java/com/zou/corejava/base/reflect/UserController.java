package com.zou.corejava.base.reflect;

public class UserController {

    @AutoWired2
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

}


class UserService{

}