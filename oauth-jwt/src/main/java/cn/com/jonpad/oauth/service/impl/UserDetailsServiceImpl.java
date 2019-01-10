package cn.com.jonpad.oauth.service.impl;

import cn.com.jonpad.api.vo.Result;
import cn.com.jonpad.api.vo.UserVo;
import cn.com.jonpad.oauth.entity.SysRole;
import cn.com.jonpad.oauth.entity.SysUser;
import cn.com.jonpad.oauth.service.SysRoleService;
import cn.com.jonpad.oauth.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 提供Security查找用户接口
 * @author Jon Chan
 * @date 2018/12/12 22:20
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService userService;
    @Autowired
    SysRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<SysUser> userResult = userService.findByUsername(username);
        if(userResult.isNull()){
            throw new UsernameNotFoundException("user:" + username + ",does not exist!");
        }

        Result<List<SysRole>> roles = roleService.getRoleByUserId(userResult.getData().getId());

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = null;
        if(!roles.isNull()){
            simpleGrantedAuthorities = roles.getData().stream().map(item -> new SimpleGrantedAuthority(item.getName())).collect(Collectors.toList());
        }else{
            simpleGrantedAuthorities = new ArrayList<>();
        }


        return new SysUser(username, userResult.getData().getPassword(), simpleGrantedAuthorities);
    }
}
