package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.UserCreationRequestDTO;
import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.business.IService.IUserService;
import com.quickbyte.common.exceptions.InvalidCredentialsException;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.UserNotFoundException;
import com.quickbyte.common.exceptions.UserAlreadyExistsException;
import com.quickbyte.data.DataModels.*;
import com.quickbyte.data.IRepositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreationRequestDTO userCreationDTO) {
        validateUserCreationInput(userCreationDTO);

        if (userRepository.existsByUsername(userCreationDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(userCreationDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setEmail(userCreationDTO.getEmail());
        user.setPasswordHash(hashPassword(userCreationDTO.getPassword()));
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());
        user.setPhoneNumber(userCreationDTO.getPhoneNumber());
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setLoyaltyPoints(0);
        user.setCardNumber(userCreationDTO.getCardNumber());
        user.setExpiryMonth(userCreationDTO.getExpiryMonth());
        user.setExpiryYear(userCreationDTO.getExpiryYear());
        user.setIsDefaultCard(userCreationDTO.getIsDefaultCard());

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::convertToDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setIsActive(userDTO.getIsActive());

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!user.getPasswordHash().equals(hashPassword(password))) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return convertToDTO(user);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserDTO> searchUsers(String searchTerm) {
        return userRepository.findByUsernameContainingOrEmailContainingOrFirstNameContainingOrLastNameContaining(
                        searchTerm, searchTerm, searchTerm, searchTerm)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<UserDTO> getUsersByRole(String role) {
//        List<User> users = userRepository.findByUserRole(role);
//        return users.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    private void validateUserCreationInput(UserCreationRequestDTO dto) {
        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            throw new InvalidInputException("Username cannot be empty");
        }
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty");
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Password cannot be empty");
        }
        // Add more validations as needed
    }

    private String hashPassword(String password) {
        // Implement proper password hashing here
        return password;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setIsActive(user.getIsActive());
        dto.setLoyaltyPoints(user.getLoyaltyPoints());
        dto.setCardNumber(user.getCardNumber());
        dto.setExpiryMonth(user.getExpiryMonth());
        dto.setExpiryYear(user.getExpiryYear());
        dto.setCVV(user.getCVV());
        dto.setIsDefaultCard(user.getIsDefaultCard());
        return dto;
    }
}