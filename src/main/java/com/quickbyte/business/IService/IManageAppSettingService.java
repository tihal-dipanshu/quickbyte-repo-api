package com.quickbyte.business.IService;

import com.quickbyte.business.DTO.AppSettingDTO;
import java.util.List;

public interface IManageAppSettingService {
    List<AppSettingDTO> getAllAppSettings();
    AppSettingDTO getAppSettingById(Integer settingId);
    AppSettingDTO createAppSetting(AppSettingDTO appSettingDTO);
    AppSettingDTO updateAppSetting(Integer settingId, AppSettingDTO appSettingDTO);
    void deleteAppSetting(Integer settingId);
    AppSettingDTO getAppSettingByOwnerId(Integer ownerId);
}