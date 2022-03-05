package com.jiahe.jiahecloudspringboot.mapper;

import com.jiahe.jiahecloudspringboot.common.entity.Department;

import java.util.List;
import java.util.Map;

/**
 * 辅助帮助接口
 */
public interface DepartmentMapper {

    /**
     * 获取所有的
     * @return
     * @throws Exception
     */
    public List<Map> loadAllDept()throws Exception;

    public int deptAdd(Department department)throws Exception;

    public void delDeptById(Map params)throws Exception;

    public void updateDeptById(Map params)throws Exception;
}
