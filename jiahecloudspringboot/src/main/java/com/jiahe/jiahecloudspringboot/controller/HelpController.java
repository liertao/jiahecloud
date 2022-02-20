package com.jiahe.jiahecloudspringboot.controller;

import com.jiahe.jiahecloudspringboot.service.HelpInputService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelpController {

    @Resource
    private HelpInputService helpInputService = null;

    //查询所有项目信息
    @RequestMapping("/project/query")
    public Map queryProject(@RequestParam Map<String, String> params, HttpServletResponse response){
        Map result = new HashMap();
        List list = new ArrayList();
        try{
            list = helpInputService.loadPorjects(params);
//            //加载项目之前  处理id问题
//            helpInputService.manProIds();
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

    //查询所有费用类型信息
    @RequestMapping("/costtype/query")
    public Map queryCostType(@RequestParam Map<String, String> params){
        Map result = new HashMap();
        List list = new ArrayList();
        try{
            list = helpInputService.loadCostType();
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

    //根据年份 查询当年所有的日期  表  work_date
    @RequestMapping("/workdates/query")
    public Map loadDateByYear(@RequestParam Map<String, String> params){
        Map result = new HashMap();
        List list = new ArrayList();
        try{
            list = helpInputService.loadDateByYear(params);
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
}
