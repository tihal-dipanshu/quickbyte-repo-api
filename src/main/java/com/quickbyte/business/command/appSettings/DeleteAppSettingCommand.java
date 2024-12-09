package com.quickbyte.business.command.appSettings;

import com.quickbyte.common.exceptions.AppSettingsNotFoundException;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;

public class DeleteAppSettingCommand implements AppSettingCommand<Void> {
    private final IManageAppSettingRepository manageAppSettingRepository;
    private final Integer settingId;

    public DeleteAppSettingCommand(IManageAppSettingRepository manageAppSettingRepository, Integer settingId) {
        this.manageAppSettingRepository = manageAppSettingRepository;
        this.settingId = settingId;
    }

    @Override
    public Void execute() {
        if (!manageAppSettingRepository.existsById(settingId)) {
            throw new AppSettingsNotFoundException("No app setting found for ID: " + settingId);
        }
        manageAppSettingRepository.deleteById(settingId);
        return null;
    }
}