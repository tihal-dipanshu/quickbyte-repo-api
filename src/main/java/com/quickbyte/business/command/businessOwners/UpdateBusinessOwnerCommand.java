package com.quickbyte.business.command.businessOwners;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.business.DTO.CreateBusinessOwnerDTO;
import com.quickbyte.common.exceptions.BusinessOwnerNotFoundException;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;

public class UpdateBusinessOwnerCommand implements BusinessOwnerCommand<BusinessOwnerDTO> {
    private final IBusinessOwnerRepository businessOwnerRepository;
    private final Integer ownerId;
    private final CreateBusinessOwnerDTO businessDto;

    public UpdateBusinessOwnerCommand(IBusinessOwnerRepository businessOwnerRepository,
                                      Integer ownerId,
                                      CreateBusinessOwnerDTO businessDto) {
        this.businessOwnerRepository = businessOwnerRepository;
        this.ownerId = ownerId;
        this.businessDto = businessDto;
    }

    @Override
    public BusinessOwnerDTO execute() {
        BusinessOwner existingBusiness = businessOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new BusinessOwnerNotFoundException("No business owner found for ID: " + ownerId));

        updateFields(existingBusiness, businessDto);

        BusinessOwner updatedBusiness = businessOwnerRepository.save(existingBusiness);
        return convertToDTO(updatedBusiness);
    }

    private void updateFields(BusinessOwner existing, CreateBusinessOwnerDTO dto) {
        existing.setUsername(dto.getUsername());
        existing.setEmail(dto.getEmail());
        existing.setPasswordHash(dto.getPasswordHash());
        existing.setContactNumber(dto.getContactNumber());
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
