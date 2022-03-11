package com.jiahe.jiahecloudspringboot.controller;

import com.google.gson.JsonArray;
import com.jiahe.jiahecloudspringboot.common.entity.Department;
import com.jiahe.jiahecloudspringboot.service.DepartmentService;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class DepartmentController {

    @Resource
    private DepartmentService departmentService = null;

    //查询所有部门信息
    @RequestMapping("/department/query")
    public Map queryDepartment(@RequestParam Map<String, String> params){
        Map result = new HashMap();
        List list = new ArrayList();
        try{
            list = departmentService.loadAllDept();
            if (list.size()==0){
                result.put("success",false);
                result.put("msg", "没有查询到数据");
                return result;
            }
            result.put("success",true);
            result.put("msg", "查询成功！");
            result.put("root", list);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/department/queryDeptToTree")
    public Map queryDeptToTree(){
        Map result = new HashMap();
        List list = new ArrayList();
        try{
            list = departmentService.loadAllDept();
            if (list.size()==0){
                result.put("success",false);
                result.put("msg", "没有查询到数据");
                return result;
            }
            result.put("success",true);
            result.put("msg", "查询成功！");
            result.put("root", list);
            result.put("code", 20000);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    //保存部门信息
    @RequestMapping("/department/add")
    public Map addDepartment(@RequestParam Map<String, String> params, HttpServletResponse response){
        Map result = new HashMap();
        try{
            Long id = Long.valueOf(params.get("id"));
            if(id > 0) {
                departmentService.updateDeptById(params);
                result.put("success", true);
                result.put("msg", "保存成功");
                return result;
            }
            Department department = new Department();
            department.setDepartmentCode(params.get("departmentCode"));
            department.setDepartmentName(params.get("departmentName"));
            int count = departmentService.deptAdd(department);
            result.put("success",true);
            result.put("msg", "操作成功！");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }
    @RequestMapping("/department/delete")
    public Map delDept(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Map result = new HashMap();
        try {
            departmentService.delDeptById(params);
            result.put("success", true);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

}
