package com.security.oauth.repository;

import com.security.oauth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName RoleRepository
 * @Description RoleRepository
 * @Author zzm
 * @Data 2019/6/6 12:04
 * @Version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "SELECT R.id,R.name FROM role R LEFT JOIN user_role UR ON R.id=UR.role_id WHERE UR.user_id=?1", nativeQuery = true)
    List<Role> findRolesByUserId(String userId);

}
