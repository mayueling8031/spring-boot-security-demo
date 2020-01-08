package com.sans.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.core.dao.SysMenuDao;
import com.sans.core.entity.SysMenuEntity;
import com.sans.core.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 权限业务实现
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    /**
     * 根据用户ID查询权限集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    @Override
    public List<SysMenuEntity> selectSysMenuByUserId(Integer userId) {
        List<SysMenuEntity> mapList = new ArrayList<>();
        List<SysMenuEntity> menuEntityList = this.baseMapper.selectSysMenuByUserId(userId);
        for (int i = 0; i <menuEntityList.size() ; i++) {
            //子节点list集合
            List<SysMenuEntity> entityList = new ArrayList<>();
            for (int j = 1; j <menuEntityList.size() ; j++) {
                if (menuEntityList.get(i).getMenuId().equals(menuEntityList.get(j).getParentId())){
                        entityList.add(menuEntityList.get(j));
                }
            }
            menuEntityList.get(i).setChildren(entityList);
            mapList.add(menuEntityList.get(i));
        }
        return mapList;
    }
}