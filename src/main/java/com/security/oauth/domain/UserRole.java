package com.security.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName UserRole
 * @Description 用户--角色--中间表
 * @Author zzm
 * @Data 2019/6/12 18:42
 * @Version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    private String id;

    private String userId;

    private String roleId;
}
