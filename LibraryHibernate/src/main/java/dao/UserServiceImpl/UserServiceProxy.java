package dao.UserServiceImpl;

import java.util.List;

import dao.UserService;
import entities.User;

public class UserServiceProxy implements UserService{
    UserService implementation;
    
    protected UserServiceProxy(UserService implementation){
        this.implementation = implementation;
    }

    @Override
    public List<User> getAll() {
        return implementation.getAll();
    }

    @Override
    public User getUser(int id) {
        return implementation.getUser(id);
    }

    @Override
    public User getUser(String name) {
        return implementation.getUser(name);
    }
}
