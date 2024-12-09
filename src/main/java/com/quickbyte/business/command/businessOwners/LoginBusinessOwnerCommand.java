package com.quickbyte.business.command.businessOwners;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.common.exceptions.BusinessOwnerNotFoundException;
import com.quickbyte.common.exceptions.InvalidCredentialsException;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;

public class LoginBusinessOwnerCommand implements BusinessOwnerCommand<BusinessOwnerDTO> {
    private final IBusinessOwnerRepository businessOwnerRepository;
    private final String username;
    private final String password;

    public LoginBusinessOwnerCommand(IBusinessOwnerRepository businessOwnerRepository, String username, String password) {
        this.businessOwnerRepository = businessOwnerRepository;
        this.username = username;
        this.password = password;
    }

    @Override
    public BusinessOwnerDTO execute() {
        BusinessOwner business = businessOwnerRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessOwnerNotFoundException("No business owner found for username: " + username));

        if (!business.getPasswordHash().equals(password)) {
            throw new InvalidCredentialsException("Invalid password");
        }

        return convertToDTO(business);
    }

    private BusinessOwnerDTO convertToDTO(BusinessOwner business) {
        return new BusinessOwnerDTO(
                business.getOwnerId(),
                business.getUsername(),
                business.getEmail(),
                business.getPasswordHash(),
                business.getContactNumber(),
                business.getCreatedAt()
        );
    }
}
