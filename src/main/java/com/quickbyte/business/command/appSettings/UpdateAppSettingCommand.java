package com.quickbyte.business.command.appSettings;

import com.quickbyte.business.DTO.AppSettingDTO;
import com.quickbyte.common.exceptions.AppSettingsNotFoundException;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.DataModels.ManageAppSetting;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;

public class UpdateAppSettingCommand implements AppSettingCommand<AppSettingDTO> {
    private final IManageAppSettingRepository manageAppSettingRepository;
    private final IBusinessOwnerRepository businessOwnerRepository;
    private final Integer settingId;
    private final AppSettingDTO appDto;

    public UpdateAppSettingCommand(IManageAppSettingRepository manageAppSettingRepository,
                                   IBusinessOwnerRepository businessOwnerRepository,
                                   Integer settingId, AppSettingDTO appDto) {
        this.manageAppSettingRepository = manageAppSettingRepository;
        this.businessOwnerRepository = businessOwnerRepository;
        this.settingId = settingId;
        this.appDto = appDto;
    }

    @Override
    public AppSettingDTO execute() {
        ManageAppSetting existingApp = manageAppSettingRepository.findById(settingId)
                .orElseThrow(() -> new AppSettingsNotFoundException("No app setting found for ID: " + settingId));

        updateFields(existingApp, appDto);

        if (appDto.getOwnerId() != null) {
            BusinessOwner owner = businessOwnerRepository.findById(appDto.getOwnerId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid owner ID"));
            existingApp.setOwner(owner);
        }

        ManageAppSetting updatedApp = manageAppSettingRepository.save(existingApp);
        return convertToDTO(updatedApp);
    }

    private void updateFields(ManageAppSetting existing, AppSettingDTO dto) {
        existing.setBusinessName(dto.getBusinessName());
        existing.setLogoUrl(dto.getLogoUrl());
        existing.setPrimaryColor(dto.getPrimaryColor());
        existing.setSecondaryColor(dto.getSecondaryColor());
        existing.setSlogan(dto.getSlogan());
    }

    private AppSettingDTO convertToDTO(ManageAppSetting app) {
        return new AppSettingDTO(
                app.getSettingId(),
                app.getOwner().getOwnerId(),
                app.getBusinessName(),
                app.getLogoUrl(),
                app.getPrimaryColor(),
                app.getSecondaryColor(),
                app.getSlogan()
        );
    }
}
