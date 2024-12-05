package com.quickbyte.data.IRepositories;

import com.quickbyte.data.DataModels.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManageMenuItemRepository extends JpaRepository<MenuItem, Integer> {
}