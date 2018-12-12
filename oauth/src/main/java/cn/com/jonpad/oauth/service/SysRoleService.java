package cn.com.jonpad.oauth.service;

import cn.com.jonpad.api.vo.Result;
import cn.com.jonpad.oauth.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jon Chan
 * @since 2018-12-13
 */
public interface SysRoleService extends IService<SysRole> {

    Result<SysRole> getRoleByUserId(Long userId);
}
