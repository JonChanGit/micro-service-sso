package cn.com.jonpad.oauth.service.impl;

import cn.com.jonpad.oauth.entity.SysUser;
import cn.com.jonpad.oauth.mapper.SysUserMapper;
import cn.com.jonpad.oauth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jon Chan
 * @since 2018-12-12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
