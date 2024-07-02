/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import config.MySQLConfig;
import entity.RoleEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ACER
 */
public class RoleRepository {
    public int addRoles(String name, String description) {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO roles (name, description) VALUES (?, ?)";
            connection = MySQLConfig.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            result = preparedStatement.executeUpdate();           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoleRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return result;
    }
    
    public List<RoleEntity> getAllRoles() {
        List<RoleEntity> roleEntityList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM roles";
            connection = MySQLConfig.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(resultSet.getInt("id"));
                roleEntity.setName(resultSet.getString("name"));
                roleEntity.setDescription("description");
                roleEntityList.add(roleEntity);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null && preparedStatement != null && resultSet != null) {
                    connection.close();
                    preparedStatement.close();
                    resultSet.close();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(RoleRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roleEntityList;
        
    }
    
}
