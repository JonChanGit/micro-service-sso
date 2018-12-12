package cn.com.jonpad.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import cn.com.jonpad.oauth.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Jon Chan
 * @since 2018-12-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String avatar;

    private String username;

    private String password;

    private String salt;

    private String name;

    private LocalDateTime birthday;

    private Integer sex;

    private String email;

    private String phone;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
