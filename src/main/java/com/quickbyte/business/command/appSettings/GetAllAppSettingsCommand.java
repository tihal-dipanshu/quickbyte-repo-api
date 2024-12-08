package com.quickbyte.business.command.appSettings;

import com.quickbyte.business.DTO.AppSettingDTO;
import com.quickbyte.data.DataModels.ManageAppSetting;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllAppSettingsCommand implements AppSettingCommand<List<AppSettingDTO>> {
    private final IManageAppSettingRepository manageAppSettingRepository;

    public GetAllAppSettingsCommand(IManageAppSettingRepository manageAppSettingRepository) {
        this.manageAppSettingRepository = manageAppSettingRepository;
    }

    @Override
    public List<AppSettingDTO> execute() {
        return manageAppSettingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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