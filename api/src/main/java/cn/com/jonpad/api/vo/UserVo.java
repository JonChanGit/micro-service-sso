package cn.com.jonpad.api.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Jon Chan
 * @date 2018/12/13 0:05
 */
@Data
public class UserVo {
    private static final long serialVersionUID = 3881610071550902762L;

    private Integer id;

    private String avatar;

    private String username;

    private String password;

    private String salt;

    private String name;

    private Date birthday;

    private Integer sex;

    private String email;

    private String phone;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}
