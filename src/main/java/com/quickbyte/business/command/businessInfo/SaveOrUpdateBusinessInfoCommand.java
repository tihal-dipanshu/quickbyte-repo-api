package com.quickbyte.business.command.businessInfo;

import com.quickbyte.business.DTO.BusinessInfoDTO;
import com.quickbyte.data.DataModels.AppSetting;
import com.quickbyte.data.IRepositories.IAppSettingRepository;

public class SaveOrUpdateBusinessInfoCommand implements BusinessInfoCommand<BusinessInfoDTO> {
    private final IAppSettingRepository appSettingRepository;
    private final BusinessInfoDTO businessInfoDTO;

    public SaveOrUpdateBusinessInfoCommand(IAppSettingRepository appSettingRepository, BusinessInfoDTO businessInfoDTO) {
        this.appSettingRepository = appSettingRepository;
        this.businessInfoDTO = businessInfoDTO;
    }

    @Override
    public BusinessInfoDTO execute() {
        AppSetting settings = appSettingRepository.findByOwnerId(businessInfoDTO.getOwnerId())
                .orElse(new AppSetting());

        updateSettings(settings, businessInfoDTO);

        AppSetting savedSettings = appSettingRepository.save(settings);
        return convertToBusinessInfoDTO(savedSettings);
    }

    private void updateSettings(AppSetting settings, BusinessInfoDTO dto) {
        settings.setOwnerId(dto.getOwnerId());
        settings.setBusinessName(dto.getBusinessName());
        settings.setLogoUrl(dto.getLogoUrl());
        settings.setPrimaryColor(dto.getPrimaryColor());
        settings.setSecondaryColor(dto.getSecondaryColor());
        settings.setSlogan(dto.getSlogan());
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