package dao.UserServiceImpl;

import dao.UserService;

public class UserServiceFactory {
    private UserServiceFactory(){};
    
    public static UserService getLocalUserService(){
        return new UserServiceImpl();
    }
    
    public static UserService getPicUserService(){
        return new UserServiceProxy(new UserServiceImplPic());
    } 
    
    public static UserService getPocUserService(){
        return new UserServiceProxy(new UserServiceImplPoc());
    } 
}

