package com.jiahe.jiahecloudspringboot.common.util;


import com.alibaba.druid.support.json.JSONUtils;
import com.jiahe.jiahecloudspringboot.service.HelpInputService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateUtil {

    @Autowired
    private HelpInputService helpInputService;

    /**
     * 没年度需要  更新work_dates表的数据信息
     * @throws Exception
     */
    @Test
    public void test()throws Exception{
        Map map =new HashMap();
        map.put("year", "2020");
        List<Map> list = helpInputService.loadDateByYear(map);
        Iterator<Map> it = list.iterator();
        while(it.hasNext()){
            Map map2 = it.next();
            String date = (String) map2.get("workDate");
            String httpArg = date;
            String jsonResult = WorkDayUtil.isWorkDay(httpArg);
            Map map3 = (Map) JSONUtils.parse(jsonResult);
            List<Map> arr = (List<Map>)map3.get("newslist");
            // 为0表示工作日、为1节假日、为2双休日、3为调休日（上班）
            if ((Integer) arr.get(0).get("daycode")==0) {
                //return resultObject.getFailResult("上班");
                System.out.println("0上班");
            }
            if ((Integer) arr.get(0).get("daycode")==1) {
                //return resultObject.getFailResult("1周末");
                System.out.println("1节假日");
                Map para = new HashMap();
                para.put("ddate",date);
                helpInputService.updateWorkDatesByDate(para);
            }
            if ((Integer) arr.get(0).get("daycode")==2) {
                //return resultObject.getFailResult("");
                System.out.println("2双休");
                Map para = new HashMap();
                para.put("ddate",date);
                helpInputService.updateWorkDatesByDate(para);
            }
            if ((Integer) arr.get(0).get("daycode")==3) {
                //return resultObject.getFailResult("上班");
                System.out.println("3调休上班");
            }
            Thread.sleep(250);
        }
        System.out.print("修改完成");
    }
}
