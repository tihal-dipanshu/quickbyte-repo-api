package com.quickbyte.business.command.businessOwners;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllBusinessOwnersCommand implements BusinessOwnerCommand<List<BusinessOwnerDTO>> {
    private final IBusinessOwnerRepository businessOwnerRepository;

    public GetAllBusinessOwnersCommand(IBusinessOwnerRepository businessOwnerRepository) {
        this.businessOwnerRepository = businessOwnerRepository;
    }

    @Override
    public List<BusinessOwnerDTO> execute() {
        return businessOwnerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
