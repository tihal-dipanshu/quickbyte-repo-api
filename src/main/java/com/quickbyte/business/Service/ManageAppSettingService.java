package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.AppSettingDTO;
import com.quickbyte.business.IService.IManageAppSettingService;
import com.quickbyte.business.command.appSettings.*;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    public <T> T executeCommand(AppSettingCommand<T> command) {
        return command.execute();
    }

    @Override
    public List<AppSettingDTO> getAllAppSettings() {
        return executeCommand(new GetAllAppSettingsCommand(manageAppSettingRepository));
    }

    @Override
    public AppSettingDTO getAppSettingById(Integer settingId) {
        return executeCommand(new GetAppSettingByIdCommand(manageAppSettingRepository, settingId));
    }

    @Override
    public AppSettingDTO createAppSetting(AppSettingDTO appSettingDTO) {
        return executeCommand(new CreateAppSettingCommand(manageAppSettingRepository, businessOwnerRepository, appSettingDTO));
    }

    @Override
    public AppSettingDTO updateAppSetting(Integer settingId, AppSettingDTO appDto) {
        return executeCommand(new UpdateAppSettingCommand(manageAppSettingRepository, businessOwnerRepository, settingId, appDto));
    }

    @Override
    public void deleteAppSetting(Integer settingId) {
        executeCommand(new DeleteAppSettingCommand(manageAppSettingRepository, settingId));
    }

    @Override
    public AppSettingDTO getAppSettingByOwnerId(Integer ownerId) {
        return executeCommand(new GetAppSettingByOwnerIdCommand(manageAppSettingRepository, ownerId));
    }
}