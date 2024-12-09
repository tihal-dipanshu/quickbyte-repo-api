package com.quickbyte.business.command.appSettings;

import com.quickbyte.business.DTO.AppSettingDTO;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.DataModels.ManageAppSetting;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;

public class CreateAppSettingCommand implements AppSettingCommand<AppSettingDTO> {
    private final IManageAppSettingRepository manageAppSettingRepository;
    private final IBusinessOwnerRepository businessOwnerRepository;
    private final AppSettingDTO appSettingDTO;

    public CreateAppSettingCommand(IManageAppSettingRepository manageAppSettingRepository,
                                   IBusinessOwnerRepository businessOwnerRepository,
                                   AppSettingDTO appSettingDTO) {
        this.manageAppSettingRepository = manageAppSettingRepository;
        this.businessOwnerRepository = businessOwnerRepository;
        this.appSettingDTO = appSettingDTO;
    }

    @Override
    public AppSettingDTO execute() {
        BusinessOwner owner = businessOwnerRepository.findById(appSettingDTO.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner ID"));

        ManageAppSetting appSetting = convertToEntity(appSettingDTO);
        appSetting.setOwner(owner);

        ManageAppSetting savedAppSetting = manageAppSettingRepository.save(appSetting);
        return convertToDTO(savedAppSetting);
    }

    private ManageAppSetting convertToEntity(AppSettingDTO dto) {
        ManageAppSetting app = new ManageAppSetting();
        app.setBusinessName(dto.getBusinessName());
        app.setLogoUrl(dto.getLogoUrl());
        app.setPrimaryColor(dto.getPrimaryColor());
        app.setSecondaryColor(dto.getSecondaryColor());
        app.setSlogan(dto.getSlogan());
        return app;
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
