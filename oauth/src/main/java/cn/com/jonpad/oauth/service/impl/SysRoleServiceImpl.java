package cn.com.jonpad.oauth.service.impl;

import cn.com.jonpad.api.vo.Result;
import cn.com.jonpad.oauth.entity.SysRole;
import cn.com.jonpad.oauth.mapper.SysRoleMapper;
import cn.com.jonpad.oauth.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jon Chan
 * @since 2018-12-13
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Result<SysRole> getRoleByUserId(Long userId) {
        return Result.getInstance(baseMapper.getRoleByUserId(userId));
    }
}
