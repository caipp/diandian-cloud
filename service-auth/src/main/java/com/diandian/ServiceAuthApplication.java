package com.diandian;

import com.diandian.domain.SysResource;
import com.diandian.domain.SysRole;
import com.diandian.domain.SysUser;
import com.diandian.enums.ResourceType;
import com.diandian.repository.SysResourceRepository;
import com.diandian.repository.SysRoleRepository;
import com.diandian.repository.SysUserRepository;
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
            private SysRoleRepository roleRepository;

            @Autowired
            private SysUserRepository userRepository;

            @Autowired
            private SysResourceRepository resourceRepository;

            @Override
            public void afterPropertiesSet() {
                //修改
                if(false){

                    SysResource sysmage= addResource("sysmage","系统管理", null,"table",1,null);
                    SysResource user= addResource("user","用户管理", "user/index","people",1,sysmage);
                    SysResource role= addResource("role","角色管理", "role/index","star",2,sysmage);
                    SysResource permission= addResource("permission","权限管理", "permission/index","lock",3,sysmage);

                    SysResource doormage= addResource("doormage","门禁管理", null,"password",2,null);
                    SysResource device= addResource("device","设备管理", "device/index","dashboard",1,doormage);
                    SysResource group= addResource("group","分组管理", "group/index","user",2,doormage);
                    SysResource card= addResource("card","门卡管理", "card/index","qq",3,doormage);
                    SysResource visitor= addResource("visitor","访客管理", "visitor/index","user",4,doormage);
                    SysResource record= addResource("record","日志管理", "record/index","message",5,doormage);

                    SysResource ossmage= addResource("ossmage","OSS管理", null,"tab",3,null);
                    SysResource oss= addResource("oss","文件列表", "oss/index","clipboard",1,ossmage);
                    SysResource config= addResource("config","云存储配置", "oss/config","icon",2,ossmage);

                    SysRole adminRole = addRole("ROLE_ADMIN","管理员角色",
                            sysmage,
                            user,
                            role,
                            permission,
                            doormage,
                            device,
                            group,
                            card,
                            visitor,
                            record,
                            ossmage,
                            oss,
                            config

                    );
                    SysRole userRole = addRole("ROLE_USER","普通用户角色");

                    addUser("admin", "admin",adminRole,userRole);
                    addUser("user", "user");




                }
            }

            private SysRole addRole(String code, String name, SysResource... sysResources) {
                SysRole role = new SysRole();
                role.setCode(code);
                role.setName(name);
                Set<SysResource> sysResourceSet = new HashSet<>();
                for( SysResource resource:sysResources){
                    sysResourceSet.add(resource);
                }
                role.setResources(sysResourceSet);

                return roleRepository.save(role);
            }


            private SysUser addUser(String username, String password , SysRole... roles) {
                SysUser user = new SysUser();
                user.setUsername(username);
                user.setNickname(username);
                user.setPassword(new BCryptPasswordEncoder().encode(password));
                Set<SysRole> roleSet = new HashSet<>();
                for( SysRole role:roles){
                    roleSet.add(role);
                }
                user.setRoles(roleSet);
                return userRepository.save(user);
            }

            private SysResource addResource (String code, String name, String address, String Icon, int orderNo, SysResource parent) {
                SysResource resource = new SysResource();
                resource.setCode(code);
                resource.setName(name);
                resource.setAddress(address);
                resource.setIcon(Icon);
                resource.setOrderNum(orderNo);
                resource.setParent(parent);
                resource.setType(ResourceType.MENU);
                return resourceRepository.save(resource);
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
