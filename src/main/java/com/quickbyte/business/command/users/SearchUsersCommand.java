package com.quickbyte.business.command.users;

import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.business.IService.IUserService;
import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IUserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchUsersCommand implements UserCommand<List<UserDTO>> {
    private final IUserRepository userRepository;
    private final String searchTerm;

    public SearchUsersCommand(IUserRepository userRepository, String searchTerm) {
        this.userRepository = userRepository;
        this.searchTerm = searchTerm;
    }

    @Override
    public List<UserDTO> execute() {
        return userRepository.findByUsernameContainingOrEmailContainingOrFirstNameContainingOrLastNameContaining(
                        searchTerm, searchTerm, searchTerm, searchTerm)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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