/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import config.MySQLConfig;
import entity.RoleEntity;
import entity.UserEntity;
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
public class UserRepository {
    
    public int deleteUserById(int id) {
        
        int rowCount = 0;
        String query = "DELETE FROM users u WHERE u.id = ?";
        Connection connection =  null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MySQLConfig.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rowCount = preparedStatement.executeUpdate();      
        } catch (Exception e) {
            System.out.println("ERROR!!!" + e.getMessage());
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rowCount;
        
    }
    
    
    public int addUser (String password, String first_name, String last_name, String username, String phone, int idRole) {
        int result = 0;
        Connection connection =  null;
        PreparedStatement preparedStatement = null;
        
        try {
            String query = "INSERT INTO users (password, first_name, last_name, username, phone, id_role) VALUES (?, ?, ?, ?, ?, ?)";
            connection = MySQLConfig.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "password");
            preparedStatement.setString(2,"first_name");
            preparedStatement.setString(3, "last_name");
            preparedStatement.setString(4, "username");
            preparedStatement.setString(5, "phone");
            preparedStatement.setString(6, "id_role");
            result = preparedStatement.executeUpdate();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return result;
    }
    
    public List<UserEntity> getUser() {
        List<UserEntity> listUser = new ArrayList<>();
        String query = "SELECT u.id, u.first_name, u.last_name, u.username, r.name FROM users u JOIN roles r ON u.id_role = r.id";
        Connection connection =  null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConfig.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setFirstName(resultSet.getString("first_name"));
                userEntity.setLastName(resultSet.getString("last_name"));
                userEntity.setUserName(resultSet.getString("username"));
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setName(resultSet.getString("name"));//do role kiểu dữ liệu RoleEntity nên phải tạo đồi tượng ra để set và lưu lại vào UserEntity
                userEntity.setRole(roleEntity);
                listUser.add(userEntity);
            }
            
            
            
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listUser;
    }
    
    
    
    
    
    
    
}
