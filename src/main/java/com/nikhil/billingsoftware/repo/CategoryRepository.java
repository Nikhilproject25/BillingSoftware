package com.nikhil.billingsoftware.repo;

import com.nikhil.billingsoftware.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

     Optional<CategoryEntity> findCategoryEntityByName(String name);
}
