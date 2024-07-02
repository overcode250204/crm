/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.RoleEntity;
import java.util.List;
import repository.RoleRepository;

/**
 *
 * @author ACER
 */
public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();
    
    public boolean addRoles(String role, String description) {
        boolean result = false;
        try {
            int check = roleRepository.addRoles(role, description);
            if (check > 0) {
                result = true;
            } else {
                System.out.println("No values were added!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        return result;
    }
    
    public List<RoleEntity> getAllRoles() {
        return roleRepository.getAllRoles();
    }
    
    
}
