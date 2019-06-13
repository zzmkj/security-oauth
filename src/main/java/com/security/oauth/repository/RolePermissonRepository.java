package com.security.oauth.repository;

import com.security.oauth.domain.RolePermisson;
import com.security.oauth.domain.RolePermissonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName RolePermissonRepository
 * @Description RolePermissonRepository
 * @Author zzm
 * @Data 2019/6/12 18:53
 * @Version 1.0
 */
@Transactional
public interface RolePermissonRepository extends JpaRepository<RolePermisson, String> {

    @Query(value = "SELECT A.NAME AS roleName,C.url AS url FROM role AS A LEFT JOIN role_permisson B ON A.id=B.role_id LEFT JOIN permission AS C ON B.permisson_id=C.id", nativeQuery = true)
    List<RolePermissonData> getRolePermissions();
}
