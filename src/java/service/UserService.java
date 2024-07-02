/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.RoleEntity;
import entity.UserEntity;
import java.util.List;
import repository.RoleRepository;
import repository.UserRepository;

/**
 *
 * @author ACER
 */
public class UserService implements I_UserService{
    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();
    @Override
    public int addUsers(String password, String fullname, String username, String phone, int idRole) {
        String[] names = fullname.split(" ");
        String lastName = names[0];
        String firstName = "";
        for (int i = 0; i < names.length; i++) {
            firstName += names[i] + " ";
        }
        firstName = firstName.trim();
        return userRepository.addUser(password, firstName, lastName, username, phone, idRole);   
    }

    @Override
    public List<RoleEntity> getAllRole() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UserEntity> getUser() {
        return userRepository.getUser();
    }

    @Override
    public boolean deleteUser(int id) {
        int count = userRepository.deleteUserById(id);
        return count > 0;
    }
    
    
}
