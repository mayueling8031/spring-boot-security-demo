package com.sans.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.core.entity.SysMenuEntity;

import java.util.List;

/**
 * @Description 权限业务接口
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
public interface SysMenuService extends IService<SysMenuEntity> {
    /**
     * 根据用户ID查询权限集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<SysMenuEntity> selectSysMenuByUserId(Integer userId);

}