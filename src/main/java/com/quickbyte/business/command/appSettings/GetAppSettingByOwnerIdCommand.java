package com.quickbyte.business.command.appSettings;

import com.quickbyte.business.DTO.AppSettingDTO;
import com.quickbyte.common.exceptions.AppSettingsNotFoundException;
import com.quickbyte.data.DataModels.ManageAppSetting;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;

public class GetAppSettingByOwnerIdCommand implements AppSettingCommand<AppSettingDTO> {
    private final IManageAppSettingRepository manageAppSettingRepository;
    private final Integer ownerId;

    public GetAppSettingByOwnerIdCommand(IManageAppSettingRepository manageAppSettingRepository, Integer ownerId) {
        this.manageAppSettingRepository = manageAppSettingRepository;
        this.ownerId = ownerId;
    }

    @Override
    public AppSettingDTO execute() {
        return manageAppSettingRepository.findByOwner_OwnerId(ownerId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new AppSettingsNotFoundException("No app setting found for owner ID: " + ownerId));
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