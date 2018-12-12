package cn.com.jonpad.oauth.service.impl;

import cn.com.jonpad.api.vo.Result;
import cn.com.jonpad.api.vo.UserVo;
import cn.com.jonpad.oauth.entity.SysUser;
import cn.com.jonpad.oauth.service.SysRoleService;
import cn.com.jonpad.oauth.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;

        roleService.getRoleByUserId(userResult.getData().getId());

        return null;
    }
}
