package com.quickbyte.api.controllers;

import com.quickbyte.business.DTO.AppSettingDTO;
import com.quickbyte.business.IService.IManageAppSettingService;
import com.quickbyte.common.exceptions.AppSettingsNotFoundException;
import com.quickbyte.common.exceptions.ErrorResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app-settings")
public class AppSettingsController {

    private final IManageAppSettingService manageAppSettingService;

    @Autowired
    public AppSettingsController(IManageAppSettingService manageAppSettingService) {
        this.manageAppSettingService = manageAppSettingService;
    }

    @GetMapping
    public ResponseEntity<List<AppSettingDTO>> getAllAppSettings() {
        List<AppSettingDTO> appSettings = manageAppSettingService.getAllAppSettings();
        return ResponseEntity.ok(appSettings);
    }

    @GetMapping("/{settingId}")
    public ResponseEntity<?> getAppSettingById(@PathVariable Integer settingId) {
        try {
            AppSettingDTO appSetting = manageAppSettingService.getAppSettingById(settingId);
            return ResponseEntity.ok(appSetting);
        } catch (AppSettingsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("App setting not found: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createAppSetting(@RequestBody AppSettingDTO newAppSetting) {
        try {
            AppSettingDTO createdAppSetting = manageAppSettingService.createAppSetting(newAppSetting);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAppSetting);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }

    @PutMapping("/{settingId}")
    public ResponseEntity<?> updateAppSetting(@PathVariable Integer settingId, @RequestBody AppSettingDTO updateAppSetting) {
        try {
            AppSettingDTO updatedAppSetting = manageAppSettingService.updateAppSetting(settingId, updateAppSetting);
            return ResponseEntity.ok(updatedAppSetting);
        } catch (AppSettingsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("App setting not found: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{settingId}")
    public ResponseEntity<?> deleteAppSetting(@PathVariable Integer settingId) {
        try {
            manageAppSettingService.deleteAppSetting(settingId);
            return ResponseEntity.noContent().build();
        } catch (AppSettingsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("App setting not found: " + e.getMessage()));
        }
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<?> getAppSettingsByOwnerId(@PathVariable Integer ownerId) {
        try {
            AppSettingDTO appSettings = manageAppSettingService.getAppSettingByOwnerId(ownerId);
            return ResponseEntity.ok(appSettings);
        } catch (AppSettingsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("No app settings found for owner ID: " + e.getMessage()));
        }
    }
}