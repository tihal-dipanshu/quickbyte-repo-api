package com.quickbyte.data.IRepositories;

import com.quickbyte.data.DataModels.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppSettingRepository extends JpaRepository<AppSetting, Integer> {
    Optional<AppSetting> findByOwnerId(int ownerId);
}