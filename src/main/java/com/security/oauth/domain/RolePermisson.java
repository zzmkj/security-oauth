package com.security.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName RolePermisson
 * @Description 角色--权限 中间表
 * @Author zzm
 * @Data 2019/6/6 12:15
 * @Version 1.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RolePermisson {

    @Id
    private String id;

    private String roleId;

    private String permissonId;
}
