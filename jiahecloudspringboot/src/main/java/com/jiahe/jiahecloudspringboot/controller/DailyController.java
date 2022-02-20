package com.jiahe.jiahecloudspringboot.controller;

import com.jiahe.jiahecloudspringboot.common.entity.ProjectCost;
import com.jiahe.jiahecloudspringboot.common.util.WorkDayUtil;
import com.jiahe.jiahecloudspringboot.service.DailyService;
import com.jiahe.jiahecloudspringboot.service.HelpInputService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class DailyController {

    @Resource
    private DailyService dailyService = null;
    @Resource
    private HelpInputService helpInputService = null;

    @RequestMapping("/daily/query")
    public Map queryDaily(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Map result = new HashMap();
        List list = new ArrayList();
        try {
            list = dailyService.loadDaily(params);
            if (list.size() == 0) {
                result.put("success", false);
                result.put("msg", "没有查询到数据");
                result.put("continueFlag", false);
                return result;
            }
            result.put("success", true);
            result.put("continueFlag", true);
            result.put("msg", "查询成功！");
            result.put("root", list);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/daily/dailyCalendar")
    public Map queryDailyCalendar(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Map result = new HashMap();
        List data = new ArrayList();//当年已经填写日志日期列表
        List cdata = new ArrayList();//当年未填写日志日期列表
        try {
            data = dailyService.loadDailyCurrentWorkDate(params);
            cdata = dailyService.loadUNWriteWorkDate(params);
            if (data.size() == 0) {
                result.put("success", false);
                result.put("msg", "没有查询到数据");
                return result;
            }
            String[] data1 = list2Arr(data);
            String[] data2 = list2Arr(cdata);
            result.put("success", true);
            result.put("msg", "查询成功！");
            result.put("data", data1);
            result.put("cdata", data2);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/daily/queryDailyByDate")
    public Map loadDailyByDate(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Map result = new HashMap();
        List list = new ArrayList();
        try {
            list = dailyService.loadDailyByDate(params);
            result.put("success", true);
            result.put("msg", "查询成功！");
            result.put("root", list);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/daily/add")
    public Map dailyAdd(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Map result = new HashMap();
        try {
            ProjectCost projectCost = new ProjectCost();
            projectCost.setProjectNo(params.get("projectNo"));
            projectCost.setCostType(params.get("costType"));
            projectCost.setCostHours(Double.valueOf(params.get("costHours")));
            projectCost.setUnitCost(Double.valueOf(params.get("unitCost")));
            projectCost.setCreateUser(params.get("createUser"));
            projectCost.setCreateDate(new Date());
            projectCost.setWorkDate(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("workDate")));
            projectCost.setWorkAdd(params.get("workAdd"));
            projectCost.setComments(params.get("comments"));
            //加载项目之前  处理id问题
            helpInputService.manProIds();
            dailyService.dailyAdd(projectCost);
            result.put("success", true);
            result.put("msg", "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/daily/delete")
    public Map delDaily(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Map result = new HashMap();
        try {
            dailyService.delDailyById(params);
            result.put("success", true);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    public static String[] list2Arr(List list) {
        Iterator<Map> it = list.iterator();
        String[] arr = new String[list.size()];
        int i = 0;
        while (it.hasNext()) {
            Map cell = it.next();
            arr[i++] = (String) cell.get("workDate");
        }
        return arr;
    }
}
