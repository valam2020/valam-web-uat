package com.valam.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.model.ImageData;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String name);
    
    @Query(nativeQuery = true, value="select * from image_data where id = :id ")
    Optional<ImageData> findByDriverId(Long id);
    
    @Modifying
	@Transactional
	@Query(nativeQuery = true, value="Update image_data SET imagedata = :image where id = :id")
    public void updateImageById(@Param("image") ImageData imageData,@Param("id") Long id);
}
