package com.security.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName Permission
 * @Description 权限
 * @Author zzm
 * @Data 2019/6/6 12:14
 * @Version 1.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    private String id;

    private String name;

    private String url;

    private String description;

    private String pid;

}
