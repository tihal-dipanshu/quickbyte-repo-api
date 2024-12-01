package com.quickbyte.business.IService;


import com.quickbyte.business.DTO.BusinessInfoDTO;

public interface IBusinessInfoService {
    BusinessInfoDTO getBusinessInfoById(int id);
    BusinessInfoDTO saveBusinessInfo(BusinessInfoDTO businessInfoDTO);
    BusinessInfoDTO updateBusinessInfo(int id, BusinessInfoDTO businessInfoDTO);
}
