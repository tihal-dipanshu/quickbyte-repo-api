package com.quickbyte.business.command.users;

import com.quickbyte.business.DTO.UserCreationRequestDTO;
import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.common.exceptions.UserAlreadyExistsException;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IUserRepository;

import java.time.LocalDateTime;

public class CreateUserCommand implements UserCommand<UserDTO> {
    private final IUserRepository userRepository;
    private final UserCreationRequestDTO userCreationDTO;

    public CreateUserCommand(IUserRepository userRepository, UserCreationRequestDTO userCreationDTO) {
        this.userRepository = userRepository;
        this.userCreationDTO = userCreationDTO;
    }

    @Override
    public UserDTO execute() throws UserAlreadyExistsException, InvalidInputException {
        if (userRepository.existsByUsername(userCreationDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(userCreationDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = convertToUser(userCreationDTO);

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPasswordHash(user.getPasswordHash());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setIsActive(user.getIsActive());
        dto.setCardNumber(user.getCardNumber());
        dto.setExpiryMonth(user.getExpiryMonth());
        dto.setExpiryYear(user.getExpiryYear());
        dto.setCVV(user.getCVV());
        dto.setLoyaltyPoints(user.getLoyaltyPoints());
        dto.setIsDefaultCard(user.getIsDefaultCard());

        return dto;
    }

    public User convertToUser(UserCreationRequestDTO userCreationDTO) {
        User user = new User();

        user.setUsername(userCreationDTO.getUsername());
        user.setEmail(userCreationDTO.getEmail());
        user.setPasswordHash(userCreationDTO.getPassword());
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());
        user.setPhoneNumber(userCreationDTO.getPhoneNumber());
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setLoyaltyPoints(0);
        user.setCardNumber(userCreationDTO.getCardNumber());
        user.setExpiryMonth(userCreationDTO.getExpiryMonth());
        user.setExpiryYear(userCreationDTO.getExpiryYear());
        user.setCVV(userCreationDTO.getCVV());
        user.setIsDefaultCard(userCreationDTO.getIsDefaultCard());

        return user;
    }
}
