package com.security.oauth.repository;

import com.security.oauth.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PermissionRepository
 * @Description PermissionRepository
 * @Author zzm
 * @Data 2019/6/6 12:14
 * @Version 1.0
 */
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
