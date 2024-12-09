package com.quickbyte.business.command.businessOwners;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.business.DTO.CreateBusinessOwnerDTO;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;

import java.sql.Timestamp;

public class CreateBusinessOwnerCommand implements BusinessOwnerCommand<BusinessOwnerDTO> {
    private final IBusinessOwnerRepository businessOwnerRepository;
    private final CreateBusinessOwnerDTO businessDto;

    public CreateBusinessOwnerCommand(IBusinessOwnerRepository businessOwnerRepository, CreateBusinessOwnerDTO businessDto) {
        this.businessOwnerRepository = businessOwnerRepository;
        this.businessDto = businessDto;
    }

    @Override
    public BusinessOwnerDTO execute() {
        if (businessDto.getUsername() == null || businessDto.getEmail() == null) {
            throw new IllegalArgumentException("Username and Email cannot be null");
        }
        if (businessOwnerRepository.existsByUsername(businessDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (businessOwnerRepository.existsByEmail(businessDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        BusinessOwner business = convertToEntity(businessDto);
        BusinessOwner savedBusiness = businessOwnerRepository.save(business);
        return convertToDTO(savedBusiness);
    }

    private BusinessOwner convertToEntity(CreateBusinessOwnerDTO dto) {
        BusinessOwner business = new BusinessOwner();
        business.setUsername(dto.getUsername());
        business.setEmail(dto.getEmail());
        business.setPasswordHash(dto.getPasswordHash());
        business.setContactNumber(dto.getContactNumber());
        business.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return business;
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
