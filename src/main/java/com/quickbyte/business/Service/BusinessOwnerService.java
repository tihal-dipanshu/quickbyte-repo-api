package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.business.DTO.CreateBusinessOwnerDTO;
import com.quickbyte.business.DTO.LoginRequestDTO;
import com.quickbyte.business.IService.IBusinessOwnerService;
import com.quickbyte.common.exceptions.BusinessOwnerNotFoundException;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusinessOwnerService implements IBusinessOwnerService {

    private final IBusinessOwnerRepository businessOwnerRepository;

    @Autowired
    public BusinessOwnerService(IBusinessOwnerRepository businessOwnerRepository) {
        this.businessOwnerRepository = businessOwnerRepository;
    }

    @Override
    public List<BusinessOwnerDTO> getAllBusinessOwners() {
        return businessOwnerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BusinessOwnerDTO getBusinessOwnerById(Integer ownerId) {
        return businessOwnerRepository.findById(ownerId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new BusinessOwnerNotFoundException("No business owner found for ID: " + ownerId));
    }

    @Override
    public BusinessOwnerDTO createBusinessOwner(CreateBusinessOwnerDTO businessDto) {
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

    @Override
    public BusinessOwnerDTO updateBusinessOwner(Integer ownerId, CreateBusinessOwnerDTO businessDto) {
        BusinessOwner existingBusiness = businessOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new BusinessOwnerNotFoundException("No business owner found for ID: " + ownerId));

        updateFields(existingBusiness, businessDto);

        BusinessOwner updatedBusiness = businessOwnerRepository.save(existingBusiness);
        return convertToDTO(updatedBusiness);
    }

    @Override
    public void deleteBusinessOwner(Integer ownerId) {
        if (!businessOwnerRepository.existsById(ownerId)) {
            throw new BusinessOwnerNotFoundException("No business owner found for ID: " + ownerId);
        }
        businessOwnerRepository.deleteById(ownerId);
    }

    private void updateFields(BusinessOwner existing, CreateBusinessOwnerDTO dto) {
        existing.setUsername(dto.getUsername());
        existing.setEmail(dto.getEmail());
        existing.setPasswordHash(dto.getPasswordHash());
        existing.setContactNumber(dto.getContactNumber());
    }

    @Override
    public BusinessOwnerDTO loginBusinessOwner(String username, String password) {
        BusinessOwner business = businessOwnerRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessOwnerNotFoundException("No business owner found for username: " + username));

        if (!business.getPasswordHash().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return convertToDTO(business);
    }

    private BusinessOwner convertToEntity(CreateBusinessOwnerDTO dto) {
        BusinessOwner business = new BusinessOwner();
        updateFields(business, dto);
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