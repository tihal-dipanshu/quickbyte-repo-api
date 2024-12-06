package com.quickbyte.data.IRepositories;

import com.quickbyte.data.DataModels.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {
    List<MenuCategory> findByIsActiveTrue();
    boolean existsById(int categoryId);
}