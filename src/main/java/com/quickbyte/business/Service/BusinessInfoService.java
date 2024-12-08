package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.BusinessInfoDTO;
import com.quickbyte.business.IService.IBusinessInfoService;
import com.quickbyte.business.command.businessInfo.BusinessInfoCommand;
import com.quickbyte.business.command.businessInfo.GetBusinessInfoByIdCommand;
import com.quickbyte.business.command.businessInfo.SaveOrUpdateBusinessInfoCommand;
import com.quickbyte.data.IRepositories.IAppSettingRepository;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessInfoService implements IBusinessInfoService {

    private final IBusinessOwnerRepository businessOwnerRepository;
    private final IAppSettingRepository appSettingRepository;

    @Autowired
    public BusinessInfoService(IBusinessOwnerRepository businessOwnerRepository, IAppSettingRepository appSettingRepository) {
        this.businessOwnerRepository = businessOwnerRepository;
        this.appSettingRepository = appSettingRepository;
    }

    public <T> T executeCommand(BusinessInfoCommand<T> command) {
        return command.execute();
    }

    @Override
    public BusinessInfoDTO getBusinessInfoById(int id) {
        return executeCommand(new GetBusinessInfoByIdCommand(businessOwnerRepository, appSettingRepository, id));
    }

    @Override
    public BusinessInfoDTO saveOrUpdateBusinessInfo(BusinessInfoDTO businessInfoDTO) {
        return executeCommand(new SaveOrUpdateBusinessInfoCommand(appSettingRepository, businessInfoDTO));
    }
}
