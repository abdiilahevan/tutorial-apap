package apap.tutorial.emsidi.service;

import java.util.List;

import apap.tutorial.emsidi.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    List<UserModel> getUserList();
    UserModel getUserByUsername(String username);
    void deleteUser(UserModel user);
    void updatePassword(UserModel user, String newPassword);
    UserModel getUserNameLogin();
    String confirmPassword (String oldPassword, String newPassword, String confirmedNewPassword);
}
