package com.sans.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 权限实体
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Data
@TableName("t_menu")
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 权限ID
	 */
	@TableId
	private String menuId;
	/**
	 * 权限名称
	 */
	private String name;

	private String parentId;

	private String menuName;

	private String url;

	private String perms;

	private String icon;

	private String type;

	private String orderNum;

	private Date createTime;

	private Date modifyTime;

	private List<SysMenuEntity> children;
}
