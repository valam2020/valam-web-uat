package com.valam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.valam.app.model.RolesTable;

@Repository
public interface RoleRepo extends JpaRepository<RolesTable,Long>{

}
