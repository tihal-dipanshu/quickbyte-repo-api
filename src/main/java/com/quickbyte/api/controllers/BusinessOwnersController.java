package com.quickbyte.api.controllers;

import com.quickbyte.business.DTO.BusinessOwnerDTO;
import com.quickbyte.business.DTO.CreateBusinessOwnerDTO;
import com.quickbyte.business.IService.IBusinessOwnerService;
import com.quickbyte.common.exceptions.ErrorResponseCustom;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.BusinessOwnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business-owners")
public class BusinessOwnersController {

    private final IBusinessOwnerService businessOwnerService;

    @Autowired
    public BusinessOwnersController(IBusinessOwnerService businessOwnerService) {
        this.businessOwnerService = businessOwnerService;
    }

    @GetMapping
    public ResponseEntity<List<BusinessOwnerDTO>> getAllBusinessOwners() {
        List<BusinessOwnerDTO> owners = businessOwnerService.getAllBusinessOwners();
        return ResponseEntity.ok(owners);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<?> updateBusinessOwner(@PathVariable Integer ownerId, @RequestBody CreateBusinessOwnerDTO updateOwner) {
        try {
            BusinessOwnerDTO updatedOwner = businessOwnerService.updateBusinessOwner(ownerId, updateOwner);
            return ResponseEntity.ok(updatedOwner);
        } catch (BusinessOwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Business owner not found: " + e.getMessage()));
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<?> deleteBusinessOwner(@PathVariable Integer ownerId) {
        try {
            businessOwnerService.deleteBusinessOwner(ownerId);
            return ResponseEntity.noContent().build();
        } catch (BusinessOwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Business owner not found: " + e.getMessage()));
        }
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<?> getBusinessOwnerById(@PathVariable Integer ownerId) {
        try {
            BusinessOwnerDTO owner = businessOwnerService.getBusinessOwnerById(ownerId);
            return ResponseEntity.ok(owner);
        } catch (BusinessOwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Business owner not found: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createBusinessOwner(@RequestBody CreateBusinessOwnerDTO newOwner) {
        try {
            BusinessOwnerDTO createdOwner = businessOwnerService.createBusinessOwner(newOwner);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOwner);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }
}