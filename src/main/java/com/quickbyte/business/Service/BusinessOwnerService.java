package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.business.DTO.CreateBusinessOwnerDTO;
import com.quickbyte.business.IService.IBusinessOwnerService;
import com.quickbyte.business.command.businessOwners.*;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BusinessOwnerService implements IBusinessOwnerService {

    private final IBusinessOwnerRepository businessOwnerRepository;

    @Autowired
    public BusinessOwnerService(IBusinessOwnerRepository businessOwnerRepository) {
        this.businessOwnerRepository = businessOwnerRepository;
    }

    public <T> T executeCommand(BusinessOwnerCommand<T> command) {
        return command.execute();
    }

    @Override
    public List<BusinessOwnerDTO> getAllBusinessOwners() {
        return executeCommand(new GetAllBusinessOwnersCommand(businessOwnerRepository));
    }

    @Override
    public BusinessOwnerDTO getBusinessOwnerById(Integer ownerId) {
        return executeCommand(new GetBusinessOwnerByIdCommand(businessOwnerRepository, ownerId));
    }

    @Override
    public BusinessOwnerDTO createBusinessOwner(CreateBusinessOwnerDTO businessDto) {
        return executeCommand(new CreateBusinessOwnerCommand(businessOwnerRepository, businessDto));
    }

    @Override
    public BusinessOwnerDTO updateBusinessOwner(Integer ownerId, CreateBusinessOwnerDTO businessDto) {
        return executeCommand(new UpdateBusinessOwnerCommand(businessOwnerRepository, ownerId, businessDto));
    }

    @Override
    public void deleteBusinessOwner(Integer ownerId) {
        executeCommand(new DeleteBusinessOwnerCommand(businessOwnerRepository, ownerId));
    }

    @Override
    public BusinessOwnerDTO loginBusinessOwner(String username, String password) {
        return executeCommand(new LoginBusinessOwnerCommand(businessOwnerRepository, username, password));
    }
}