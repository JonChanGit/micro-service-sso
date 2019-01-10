package cn.com.jonpad.oauth.mapper;

import cn.com.jonpad.oauth.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jon Chan
 * @since 2018-12-13
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleByUserId(Long userId);
}
