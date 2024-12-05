package com.quickbyte.data.IRepositories;

import com.quickbyte.data.DataModels.AppSetting;
import com.quickbyte.data.DataModels.ManageAppSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IManageAppSettingRepository extends JpaRepository<ManageAppSetting, Integer> {
    Optional<ManageAppSetting> findByOwner_OwnerId(Integer ownerId);
}