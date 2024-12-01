package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.BusinessInfoDTO;
import com.quickbyte.business.IService.IBusinessInfoService;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.AppSetting;
import com.quickbyte.data.DataModels.BusinessOwner;
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

    @Override
    public BusinessInfoDTO getBusinessInfoById(int id) {
        BusinessOwner owner = businessOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Business owner not found with id: " + id));
        AppSetting settings = appSettingRepository.findByOwnerId(owner.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("App settings not found for owner id: " + id));

        return convertToBusinessInfoDTO(owner, settings);
    }

    @Override
    public BusinessInfoDTO saveBusinessInfo(BusinessInfoDTO businessInfoDTO) {
        BusinessOwner owner = new BusinessOwner();
        owner.setBusinessName(businessInfoDTO.getBusinessName());

        owner = businessOwnerRepository.save(owner);

        AppSetting settings = new AppSetting();
        settings.setOwnerId(owner.getOwnerId());
        settings.setLogoUrl(businessInfoDTO.getLogoUrl());
        settings.setPrimaryColor(businessInfoDTO.getPrimaryColor());
        settings.setSecondaryColor(businessInfoDTO.getSecondaryColor());
        settings.setSlogan(businessInfoDTO.getSlogan());

        appSettingRepository.save(settings);

        return convertToBusinessInfoDTO(owner, settings);
    }

    @Override
    public BusinessInfoDTO updateBusinessInfo(int id, BusinessInfoDTO businessInfoDTO) {
        BusinessOwner owner = businessOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Business owner not found with id: " + id));

        AppSetting settings = appSettingRepository.findByOwnerId(owner.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("App settings not found for owner id: " + id));

        owner.setBusinessName(businessInfoDTO.getBusinessName());

        settings.setLogoUrl(businessInfoDTO.getLogoUrl());
        settings.setPrimaryColor(businessInfoDTO.getPrimaryColor());
        settings.setSecondaryColor(businessInfoDTO.getSecondaryColor());
        settings.setSlogan(businessInfoDTO.getSlogan());

        businessOwnerRepository.save(owner);
        appSettingRepository.save(settings);

        return convertToBusinessInfoDTO(owner, settings);
    }

    private BusinessInfoDTO convertToBusinessInfoDTO(BusinessOwner owner, AppSetting settings) {
        return new BusinessInfoDTO(
                owner.getBusinessName(),
                settings.getLogoUrl(),
                settings.getSlogan(),
                settings.getPrimaryColor(),
                settings.getSecondaryColor()
        );
    }
}