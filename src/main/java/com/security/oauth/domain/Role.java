package com.security.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName Role
 * @Description 角色
 * @Author zzm
 * @Data 2019/6/6 11:55
 * @Version 1.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    private String id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
