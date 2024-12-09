package com.quickbyte.business.command.users;

import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.business.IService.IUserService;
import com.quickbyte.common.exceptions.InvalidCredentialsException;
import com.quickbyte.common.exceptions.UserNotFoundException;
import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IUserRepository;

public class LoginUserCommand implements UserCommand<UserDTO> {
    private final IUserRepository userRepository;
    private final String username;
    private final String password;

    public LoginUserCommand(IUserRepository userRepository, String username, String password) {
        this.userRepository = userRepository;
        this.username = username;
        this.password = password;
    }

    @Override
    public UserDTO execute() {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!user.getPasswordHash().equals(hashPassword(password))) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return convertToDTO(user);
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