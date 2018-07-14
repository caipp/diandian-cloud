package com.diandian.security;

import com.diandian.domain.SysUser;
import com.diandian.domain.User;
import com.diandian.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 *
 * @author caipiaoping
 * @date 2017/6/9
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowcaseUsername = username.toLowerCase();
        Optional<SysUser> realUser = userRepository.findOneWithRolesByUsername(lowcaseUsername);

        return realUser.map(user -> {
//            Set<GrantedAuthority> grantedAuthorities = user.getAuthorities();
            if(!user.getEnable()){
                throw new BadCredentialsException("用户" + lowcaseUsername + "已禁用!");
            }
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantedAuthorities);
            return new User(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getLastPasswordReset(),
                    user.getWxOpenId(),
                    user.getNickname(),
                    user.getAvatarUrl(),
                    user.getGender(),
                    user.getEnable(),
                    user.getAuthorities()
                    );
        }).orElseThrow(() -> new BadCredentialsException("用户" + lowcaseUsername + "不存在!"));
    }
}
