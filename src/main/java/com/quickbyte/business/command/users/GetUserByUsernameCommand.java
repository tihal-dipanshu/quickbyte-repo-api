package com.quickbyte.business.command.users;

import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.common.exceptions.UserNotFoundException;
import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IUserRepository;

public class GetUserByUsernameCommand implements UserCommand<UserDTO> {
    private final IUserRepository userRepository;
    private final String username;

    public GetUserByUsernameCommand(IUserRepository userRepository, String username) {
        this.userRepository = userRepository;
        this.username = username;
    }

    @Override
    public UserDTO execute() {
        return userRepository.findByUsername(username)
                .map(this::convertToDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
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
        dto.setCVV(user.getCVV());
        dto.setIsDefaultCard(user.getIsDefaultCard());
        return dto;
    }
}
