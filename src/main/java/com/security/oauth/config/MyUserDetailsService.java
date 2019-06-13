package com.security.oauth.config;

import com.security.oauth.domain.Role;
import com.security.oauth.domain.User;
import com.security.oauth.repository.RoleRepository;
import com.security.oauth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MyUserDetailsS​​ervice
 * @Description 根据用户名获取该用户的所有信息，包括用户信息和权限信息
 * @Author zzm
 * @Data 2019/6/6 11:58
 * @Version 1.0
 */
@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查数据库
        User user = userRepository.findByUsername(username);
        if (user != null) {
            //查询用户权限信息
            List<Role> roleList = roleRepository.findRolesByUserId(user.getId());
            log.info("【该用户角色】 = {}", roleList);
            user.setAuthorities(roleList);
        }
        log.info("【用户】 = {}", user);
        return user;
    }
}
