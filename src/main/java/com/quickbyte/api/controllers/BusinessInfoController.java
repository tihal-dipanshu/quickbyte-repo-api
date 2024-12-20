package com.quickbyte.api.controllers;

import com.quickbyte.business.DTO.BusinessInfoDTO;
import com.quickbyte.business.IService.IBusinessInfoService;
import com.quickbyte.common.exceptions.ErrorResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quickbyte.common.exceptions.ResourceNotFoundException;


@RestController
@RequestMapping("/api/business-info")
public class BusinessInfoController {

    private final IBusinessInfoService businessInfoService;

    @Autowired
    public BusinessInfoController(IBusinessInfoService businessInfoService) {
        this.businessInfoService = businessInfoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBusinessInfo(@PathVariable int id) {
        try {
            BusinessInfoDTO businessInfo = businessInfoService.getBusinessInfoById(id);
            return ResponseEntity.ok(businessInfo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Business not found: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error fetching business information: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdateBusinessInfo(@RequestBody BusinessInfoDTO businessInfoDTO) {
        try {
            BusinessInfoDTO savedInfo = businessInfoService.saveOrUpdateBusinessInfo(businessInfoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error saving or updating business information: " + e.getMessage()));
        }
    }
}