package com.quickbyte.business.command.appSettings;

import com.quickbyte.business.DTO.AppSettingDTO;
import com.quickbyte.common.exceptions.AppSettingsNotFoundException;
import com.quickbyte.data.DataModels.ManageAppSetting;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;

public class GetAppSettingByIdCommand implements AppSettingCommand<AppSettingDTO> {
    private final IManageAppSettingRepository manageAppSettingRepository;
    private final Integer settingId;

    public GetAppSettingByIdCommand(IManageAppSettingRepository manageAppSettingRepository, Integer settingId) {
        this.manageAppSettingRepository = manageAppSettingRepository;
        this.settingId = settingId;
    }

    @Override
    public AppSettingDTO execute() {
        return manageAppSettingRepository.findById(settingId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new AppSettingsNotFoundException("No app setting found for ID: " + settingId));
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
