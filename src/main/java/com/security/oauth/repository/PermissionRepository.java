package com.security.oauth.repository;

import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName PermissionRepository
 * @Description PermissionRepository
 * @Author zzm
 * @Data 2019/6/6 12:14
 * @Version 1.0
 */
public interface PermissionRepository {

    @Query(value = "SELECT A.NAME AS roleName,C.url FROM role AS A LEFT JOIN role_permission B ON A.id=B.role_id LEFT JOIN permission AS C ON B.permission_id=C.id", nativeQuery = true)
    void test();
}
