package com.quickbyte.business.IService;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.business.DTO.CreateBusinessOwnerDTO;

import java.util.List;

public interface IBusinessOwnerService {
    List<BusinessOwnerDTO> getAllBusinessOwners();
    BusinessOwnerDTO getBusinessOwnerById(Integer ownerId);
    BusinessOwnerDTO createBusinessOwner(CreateBusinessOwnerDTO businessOwnerDTO);
    BusinessOwnerDTO updateBusinessOwner(Integer ownerId, CreateBusinessOwnerDTO businessOwnerDTO);
    void deleteBusinessOwner(Integer ownerId);
}