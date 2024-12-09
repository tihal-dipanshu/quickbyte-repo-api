package com.quickbyte.business.command.users;

import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.business.IService.IUserService;
import com.quickbyte.common.exceptions.UserNotFoundException;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IUserRepository;

public class UpdateUserCommand implements UserCommand<UserDTO> {
    private final IUserRepository userRepository;
    private final Integer userId;
    private final UserDTO userDTO;

    public UpdateUserCommand(IUserRepository userRepository, Integer userId, UserDTO userDTO) {
        this.userRepository = userRepository;
        this.userId = userId;
        this.userDTO = userDTO;
    }

    @Override
    public UserDTO execute() {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        updateUserFields(user, userDTO);

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    private void updateUserFields(User user, UserDTO userDTO) {
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPasswordHash() != null && !userDTO.getPasswordHash().isEmpty() && !userDTO.getPasswordHash().isBlank() && userDTO.getPasswordHash().length() > 0) {
            user.setPasswordHash(hashPassword(userDTO.getPasswordHash()));
        }
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setIsActive(userDTO.getIsActive());
        user.setLoyaltyPoints(userDTO.getLoyaltyPoints());
        user.setCardNumber(userDTO.getCardNumber());
        user.setExpiryMonth(userDTO.getExpiryMonth());
        user.setExpiryYear(userDTO.getExpiryYear());
        user.setCVV(userDTO.getCVV());
        user.setIsDefaultCard(userDTO.getIsDefaultCard());
    }

    private String hashPassword(String password) {
        return password;
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
        dto.setLoyaltyPoints(user.getLoyaltyPoints());
        dto.setCardNumber(user.getCardNumber());
        dto.setExpiryMonth(user.getExpiryMonth());
        dto.setExpiryYear(user.getExpiryYear());
        dto.setCVV(user.getCVV() != null ? user.getCVV() : null);
        dto.setIsDefaultCard(user.getIsDefaultCard());
        return dto;
    }
}