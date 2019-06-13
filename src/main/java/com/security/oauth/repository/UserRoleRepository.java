package com.security.oauth.repository;

import com.security.oauth.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserRoleRepository
 * @Description UserRoleRepository
 * @Author zzm
 * @Data 2019/6/12 18:53
 * @Version 1.0
 */
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}
