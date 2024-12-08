package com.quickbyte.business.command.users;

import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.business.IService.IUserService;
import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IUserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllUsersCommand implements UserCommand<List<UserDTO>> {
    private final IUserRepository userRepository;

    public GetAllUsersCommand(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> execute() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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