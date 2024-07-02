/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.RoleEntity;
import entity.UserEntity;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface I_UserService {
    List<RoleEntity> getAllRole();
    List<UserEntity> getUser();
    int addUsers(String password, String fullname, String username, String phone, int idRole);
    boolean deleteUser(int id);
}
