package com.jiahe.jiahecloudspringboot.common.util;

import com.alibaba.druid.support.json.JSONUtils;
import net.minidev.json.JSONUtil;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工作日 工具类 判断当前日期是否是工作日 0 工作日 1周末 2节假日
 */
public class WorkDayUtil {
    public static String isWorkDay(String httpArg){
        String httpUrl = "http://api.tianapi.com/txapi/jiejiari/index";
//        String httpUrl = "http://tool.bitefu.net/jiari/";
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?key=bfa13c72cb6916d15782563d5aee414e&date=" +httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取当前日期列表  当年日历列表
     * @return
     */
    public static List<String> currentDateList()throws Exception{
        Date date = new Date();
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
        List<String> fullDayList = new ArrayList<>(32);
        // 获得当前日期对象
        Calendar cal = Calendar.getInstance();
        cal.clear();// 清除信息
        cal.set(Calendar.YEAR, year);
        for(int i=1; i<=month; i++){
            // 1月从0开始
            cal.set(Calendar.MONTH, i-1 );
            // 当月1号
            cal.set(Calendar.DAY_OF_MONTH,1);
            int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int j = 1; j <= count ; j++) {
                fullDayList.add(dateFormatYYYYMMDD.format(cal.getTime()));
                cal.add(Calendar.DAY_OF_MONTH,1);
            }
        }
        return fullDayList;
//        return workList(fullDayList);
    }

    /**
     * 获取 未写日志 列表
     * @param exists
     * @param stardard
     * @return
     */
    public static List<String> unDoDailyList(List<String> exists, List<String> stardard){
        Iterator<String> it = stardard.iterator();
        while(it.hasNext()){
            String cell = it.next();
            if(exists.contains(cell))
                it.remove();
        }
        return stardard;
    }

    /**
     * 将list 转换为 工作日列表
     * @param list
     * @return
     */
    public static List<String> workList(List<String> list)throws Exception{
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String cell = it.next();
            cell = cell.replaceAll("-","");
            if(!"0".equals(WorkDayUtil.isWorkDay(cell)))
                list.remove(cell);
        }
        return list;
    }

    public static void main(String[] args){
        // 处理节假日
        String httpArg = "2020-10-11";
		/*SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		httpArg = f.format(new Date());*/
        String jsonResult = WorkDayUtil.isWorkDay(httpArg);
        Map map = (Map) JSONUtils.parse(jsonResult);
        List<Map> arr = (List<Map>)map.get("newslist");
        // 为0表示工作日、为1节假日、为2双休日、3为调休日（上班）
        if ((Integer) arr.get(0).get("daycode")==0) {
            //return resultObject.getFailResult("上班");
            System.out.println("0上班");
        }
        if ((Integer) arr.get(0).get("daycode")==1) {
            //return resultObject.getFailResult("1周末");
            System.out.println("1节假日");
        }
        if ((Integer) arr.get(0).get("daycode")==2) {
            //return resultObject.getFailResult("");
            System.out.println("2双休日");
        }
        if ((Integer) arr.get(0).get("daycode")==3) {
            //return resultObject.getFailResult("");
            System.out.println("3调休上班");
        }
//        String json = "{\"code\":200,\"msg\":\"success\",\"newslist\":[{\"date\":\"2020-10-11\",\"daycode\":2,\"weekday\":0,\"cnweekday\":\"星期日\",\"lunaryear\":\"庚子\",\"lunarmonth\":\"八月\",\"lunarday\":\"廿五\",\"info\":\"双休日\",\"start\":\"\",\"end\":\"\",\"holiday\":\"\",\"name\":\"\",\"enname\":\"\",\"isnotwork\":1,\"vacation\":\"\",\"tip\":\"\"}]}";
//        Map map = (Map) JSONUtils.parse(json);
//        List<Map> arr = (List<Map>)map.get("newslist");
//        Integer daycode = (Integer) arr.get(0).get("daycode");
//        System.out.print(daycode);
    }
}
