package apap.tutorial.emsidi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tutorial.emsidi.model.UserModel;
import apap.tutorial.emsidi.repository.UserDB;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDB userDB;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getUserList() {return userDB.findAll();}

    @Override
    public UserModel getUserByUsername(String username){
        UserModel user = userDB.findByUsername(username);
        return user;
    }

    @Override
    public void deleteUser(UserModel user) {
        userDB.delete(user);
    }

    @Override
    public void updatePassword(UserModel user, String newPassword) {
        String encryptedNewPassword = encrypt(newPassword);
        user.setPassword(encryptedNewPassword);
        userDB.save(user);
    }

    @Override
    public UserModel getUserNameLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        else {
            username = principal.toString();
        }

        return userDB.findByUsername(username);
    }

    @Override
    public String confirmPassword (String oldPassword, String newPassword, String confirmedNewPassword){
        String error = "none";
        boolean digitInPassword = false;
        boolean passwordContainsLetter = false;

        passwordContainsLetter = newPassword.matches(".*[a-zA-Z]+.*");

        if(newPassword.equals(oldPassword)){
            error = "Password baru dan password lama tidak boleh sama, proses update password dibatalkan";
        }

        if(!newPassword.equals(confirmedNewPassword)){
            error = "Password baru dan konfirmasi password baru tidak sama, proses update password dibatalkan";
        }
        
        if(newPassword.length()>=8){
            for (char i : newPassword.toCharArray()) {
                if (Character.isDigit(i)){
                    digitInPassword = true;
                    return "update-password-berhasil";
                }
            }
        }

        if(!passwordContainsLetter){
            error = "Password harus terdiri atas minimal 1 buah huruf, Proses update password dibatalkan";
        }
        if(!digitInPassword){
            error = "Password harus terdiri atas minimal 1 buah angka, Proses update password dibatalkan";
        }
        if((newPassword.length() <= 7)){
            error = "Panjang password harus terdiri dari minimal 8 karakter (huruf dan angka). Silahkan mengulang proses update password.";
        }

        UserModel user = getUserNameLogin();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!(passwordEncoder.matches(oldPassword, user.getPassword()))){
            error = "Password lama yang anda masukkan tidak sama";
        }
        return error;
    }
}
