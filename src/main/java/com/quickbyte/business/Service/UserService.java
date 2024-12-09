package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.UserCreationRequestDTO;
import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.business.IService.IUserService;
import com.quickbyte.business.command.users.*;
import com.quickbyte.data.IRepositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public <T> T executeCommand(UserCommand<T> command) {
        return command.execute();
    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreationRequestDTO userCreationDTO) {
        return executeCommand(new CreateUserCommand(userRepository, userCreationDTO));
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return executeCommand(new GetUserByIdCommand(userRepository, id));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return executeCommand(new GetUserByUsernameCommand(userRepository, username));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return executeCommand(new GetAllUsersCommand(userRepository));
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO userDTO) {
        return executeCommand(new UpdateUserCommand(userRepository, userId, userDTO));
    }

    @Override
    public void deleteUser(Integer id) {
        executeCommand(new DeleteUserCommand(userRepository, id));
    }

    @Override
    public UserDTO loginUser(String username, String password) {
        return executeCommand(new LoginUserCommand(userRepository, username, password));
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return executeCommand(new CheckUsernameTakenCommand(userRepository, username));
    }

    @Override
    public boolean isEmailTaken(String email) {
        return executeCommand(new CheckEmailTakenCommand(userRepository, email));
    }

    @Override
    public List<UserDTO> searchUsers(String searchTerm) {
        return executeCommand(new SearchUsersCommand(userRepository, searchTerm));
    }
}