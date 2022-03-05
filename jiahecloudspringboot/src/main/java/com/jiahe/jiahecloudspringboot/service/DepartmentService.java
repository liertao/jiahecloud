package com.jiahe.jiahecloudspringboot.service;

import com.jiahe.jiahecloudspringboot.common.entity.Department;
import com.jiahe.jiahecloudspringboot.mapper.DepartmentMapper;
import com.jiahe.jiahecloudspringboot.mapper.HelpInputMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 辅助帮助服务类
 */
@Service
public class DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    public List<Map> loadAllDept()throws Exception{
        return departmentMapper.loadAllDept();
    }

    public int deptAdd(Department department)throws Exception{
        return departmentMapper.deptAdd(department);
    }

    public void delDeptById(Map params)throws Exception{
        departmentMapper.delDeptById(params);
    }

    public void updateDeptById(Map params)throws Exception{
        departmentMapper.updateDeptById(params);
    }
}
