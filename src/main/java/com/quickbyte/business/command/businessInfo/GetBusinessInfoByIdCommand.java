package com.quickbyte.business.command.businessInfo;

import com.quickbyte.business.DTO.BusinessInfoDTO;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.AppSetting;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IAppSettingRepository;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;

public class GetBusinessInfoByIdCommand implements BusinessInfoCommand<BusinessInfoDTO> {
    private final IBusinessOwnerRepository businessOwnerRepository;
    private final IAppSettingRepository appSettingRepository;
    private final int id;

    public GetBusinessInfoByIdCommand(IBusinessOwnerRepository businessOwnerRepository,
                                      IAppSettingRepository appSettingRepository,
                                      int id) {
        this.businessOwnerRepository = businessOwnerRepository;
        this.appSettingRepository = appSettingRepository;
        this.id = id;
    }

    @Override
    public BusinessInfoDTO execute() {
        BusinessOwner owner = businessOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Business owner not found with id: " + id));
        AppSetting settings = appSettingRepository.findByOwnerId(id)
                .orElseThrow(() -> new ResourceNotFoundException("App settings not found for owner id: " + id));

        return convertToBusinessInfoDTO(settings);
    }

    private BusinessInfoDTO convertToBusinessInfoDTO(AppSetting settings) {
        return new BusinessInfoDTO(
                settings.getBusinessName(),
                settings.getLogoUrl(),
                settings.getSlogan(),
                settings.getPrimaryColor(),
                settings.getSecondaryColor(),
                settings.getOwnerId()
        );
    }
}
