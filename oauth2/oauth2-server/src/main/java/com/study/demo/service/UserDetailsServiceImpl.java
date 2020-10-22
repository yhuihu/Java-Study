package com.study.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Tiger
 * @date 2020-10-11
 * @see com.study.demo.service
 **/
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        UserPermissionVO userPermissionVO = tbUserService.getByUserName(s);
//        if (userPermissionVO != null) {
//            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userPermissionVO.getRoleName());
//            grantedAuthorities.add(grantedAuthority);
//            return new MyUserDetail(userPermissionVO.getId(), userPermissionVO.getUserName(), userPermissionVO.getPassword(), grantedAuthorities);
//        }
        return null;
    }
}

