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

    // GET method to retrieve business info by ID
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

    // POST method to create new business info
    @PostMapping
    public ResponseEntity<?> saveBusinessInfo(@RequestBody BusinessInfoDTO businessInfoDTO) {
        try {
            BusinessInfoDTO savedInfo = businessInfoService.saveBusinessInfo(businessInfoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error saving business information: " + e.getMessage()));
        }
    }

    // PUT method to update existing business info by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBusinessInfo(@PathVariable int id, @RequestBody BusinessInfoDTO businessInfoDTO) {
        try {
            BusinessInfoDTO updatedInfo = businessInfoService.updateBusinessInfo(id, businessInfoDTO);
            return ResponseEntity.ok(updatedInfo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Business not found: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error updating business information: " + e.getMessage()));
        }
    }
}