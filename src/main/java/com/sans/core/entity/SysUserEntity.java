package com.sans.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户实体
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Data
@TableName("t_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	@TableId
	private String userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 状态:1正常  0禁用
	 */
	private String status;

	private String deptId;

	private String email;

	private String mobile;

	private String createTime;

	private String modifyTime;

	private String lastLoginTime;

	private String ssex;

	private String isTab;

	private String theme;

	private String avatar;

	private String description;


}