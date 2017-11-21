package com.diandian;

import com.diandian.domain.SysRole;
import com.diandian.domain.SysUser;
import com.diandian.repository.RoleRepository;
import com.diandian.repository.UserRepository;
import com.diandian.security.SecurityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.security.acl.Permission;
import java.util.HashSet;
import java.util.Set;

/**
 * @author caipiaoping
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }

    @Bean
    public InitializingBean insertDefaultUsers() {
        return new InitializingBean() {

            @Autowired
            private RoleRepository roleRepository;

            @Autowired
            private UserRepository userRepository;

            @Override
            public void afterPropertiesSet() {
                //修改
                if(false){

                    SysRole adminRole = addRole("ROLE_ADMIN","管理员角色");
                    SysRole userRole = addRole("ROLE_USER","普通用户角色");

                    addUser("admin", "admin",adminRole,userRole);
                    addUser("user", "user");

                }
            }

            private SysRole addRole(String code, String name, Permission... permissions) {
                SysRole role = new SysRole();
                role.setCode(code);
                role.setName(name);
                return roleRepository.save(role);
            }


            private SysUser addUser(String username, String password , SysRole... roles) {
                SysUser user = new SysUser();
                user.setUsername(username);
                user.setPassword(new BCryptPasswordEncoder().encode(password));
                Set<SysRole> roleSet = new HashSet<>();
                for( SysRole role:roles){
                    roleSet.add(role);
                }
                user.setRoles(roleSet);
                return userRepository.save(user);
            }

        };
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean(name = "auditorAware")
    public AuditorAware<String> auditorAware() {
        return ()-> SecurityUtils.getCurrentUserUsername();
    }

}
