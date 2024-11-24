package com.quickbyte.business.IService;

import com.quickbyte.business.DTO.UserCreationRequestDTO;
import com.quickbyte.business.DTO.UserDTO;
import java.util.List;

public interface IUserService {
    UserDTO createUser(UserCreationRequestDTO userCreationDTO);
    UserDTO getUserById(Integer id);
    UserDTO getUserByUsername(String username);
   // List<UserDTO> getUsersByRole(String role);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Integer userId, UserDTO userDTO);
    void deleteUser(Integer id);
    UserDTO loginUser(String username, String password);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
    List<UserDTO> searchUsers(String searchTerm);
}