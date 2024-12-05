package com.quickbyte.business.Service;


import com.quickbyte.business.DTO.AppSettingDTO;
import com.quickbyte.business.IService.IManageAppSettingService;
import com.quickbyte.common.exceptions.AppSettingsNotFoundException;
import com.quickbyte.data.DataModels.ManageAppSetting;
import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IAppSettingRepository;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManageAppSettingService implements IManageAppSettingService {

    private final IManageAppSettingRepository manageAppSettingRepository;
    private final IBusinessOwnerRepository businessOwnerRepository;

    @Autowired
    public ManageAppSettingService(IManageAppSettingRepository manageAppSettingRepository, IBusinessOwnerRepository businessOwnerRepository) {
        this.manageAppSettingRepository = manageAppSettingRepository;
        this.businessOwnerRepository = businessOwnerRepository;
    }

    @Override
    public List<AppSettingDTO> getAllAppSettings() {
        return manageAppSettingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppSettingDTO getAppSettingById(Integer settingId) {
        return manageAppSettingRepository.findById(settingId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new AppSettingsNotFoundException("No app setting found for ID: " + settingId));
    }

//    @Override
//    public AppSettingDTO createAppSetting(AppSettingDTO appSettingDTO) {
//        return null;
//    }

    @Override
    public AppSettingDTO createAppSetting(AppSettingDTO appSettingDTO) {
        BusinessOwner owner = businessOwnerRepository.findById(appSettingDTO.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner ID"));

        ManageAppSetting appSetting = convertToEntity(appSettingDTO);
        appSetting.setOwner(owner);

        ManageAppSetting savedAppSetting = manageAppSettingRepository.save(appSetting);
        return convertToDTO(savedAppSetting);
    }

    @Override
    public AppSettingDTO updateAppSetting(Integer settingId, AppSettingDTO appDto) {
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

    @Override
    public void deleteAppSetting(Integer settingId) {
        if (!manageAppSettingRepository.existsById(settingId)) {
            throw new AppSettingsNotFoundException("No app setting found for ID: " + settingId);
        }
        manageAppSettingRepository.deleteById(settingId);
    }

    @Override
    public AppSettingDTO getAppSettingByOwnerId(Integer ownerId) {
        return manageAppSettingRepository.findByOwner_OwnerId(ownerId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new AppSettingsNotFoundException("No app setting found for owner ID: " + ownerId));
    }

    private void updateFields(ManageAppSetting existing, AppSettingDTO dto) {
        existing.setBusinessName(dto.getBusinessName());
        existing.setLogoUrl(dto.getLogoUrl());
        existing.setPrimaryColor(dto.getPrimaryColor());
        existing.setSecondaryColor(dto.getSecondaryColor());
        existing.setSlogan(dto.getSlogan());
    }

    private ManageAppSetting convertToEntity(AppSettingDTO dto) {
        ManageAppSetting app = new ManageAppSetting();
        updateFields(app, dto);
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