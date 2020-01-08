package com.sans.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * @Description 角色与权限关系实体
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Data
@TableName("t_role_menu")
public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 权限ID
	 */
	private String menuId;
}
