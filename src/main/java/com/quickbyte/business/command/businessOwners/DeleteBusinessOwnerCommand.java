package com.quickbyte.business.command.businessOwners;

import com.quickbyte.common.exceptions.BusinessOwnerNotFoundException;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;

public class DeleteBusinessOwnerCommand implements BusinessOwnerCommand<Void> {
    private final IBusinessOwnerRepository businessOwnerRepository;
    private final Integer ownerId;

    public DeleteBusinessOwnerCommand(IBusinessOwnerRepository businessOwnerRepository, Integer ownerId) {
        this.businessOwnerRepository = businessOwnerRepository;
        this.ownerId = ownerId;
    }

    @Override
    public Void execute() {
        if (!businessOwnerRepository.existsById(ownerId)) {
            throw new BusinessOwnerNotFoundException("No business owner found for ID: " + ownerId);
        }
        businessOwnerRepository.deleteById(ownerId);
        return null;
    }
}
