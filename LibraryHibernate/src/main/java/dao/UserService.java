package dao;

import java.util.List;

import entities.User;

public interface UserService {
    public List<User> getAll();
    public User getUser(int id);
    public User getUser(String name);
}
