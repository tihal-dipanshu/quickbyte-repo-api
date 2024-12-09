package com.quickbyte.business.command.businessOwners;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.common.exceptions.BusinessOwnerNotFoundException;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;

public class GetBusinessOwnerByIdCommand implements BusinessOwnerCommand<BusinessOwnerDTO> {
    private final IBusinessOwnerRepository businessOwnerRepository;
    private final Integer ownerId;

    public GetBusinessOwnerByIdCommand(IBusinessOwnerRepository businessOwnerRepository, Integer ownerId) {
        this.businessOwnerRepository = businessOwnerRepository;
        this.ownerId = ownerId;
    }

    @Override
    public BusinessOwnerDTO execute() {
        return businessOwnerRepository.findById(ownerId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new BusinessOwnerNotFoundException("No business owner found for ID: " + ownerId));
    }

    private BusinessOwnerDTO convertToDTO(BusinessOwner owner) {
        return new BusinessOwnerDTO(
                owner.getOwnerId(),
                owner.getUsername(),
                owner.getEmail(),
                owner.getPasswordHash(),
                owner.getContactNumber(),
                owner.getCreatedAt()
        );
    }
}
