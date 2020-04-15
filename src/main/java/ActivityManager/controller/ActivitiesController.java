package ActivityManager.controller;


import ActivityManager.model.ActivitiesInfo;
import ActivityManager.service.imtf.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ActivitiesController {
    @Autowired
    private ActivitiesService activitiesService;

    /**
     * 跳转到活动信息页面
     * @param model
     * @param curPage
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model,
                        @RequestParam(value = "curPage",required = false) Integer curPage){
        //页面展示数据为5条
        int pageSize=5;
        //获取当前时间
        Date date = new Date();
        //活动总数量
        int tolalRows=activitiesService.getActivitiesNum();
        //计算分页
        int totalPages=tolalRows/pageSize;
        //可能的余数
        int left= tolalRows%pageSize;
        if (left>0){
            totalPages=totalPages+1;
        }
        //如果没有传当前页,或者小于1，则设置成1
        if (null == curPage||curPage<1){
            curPage=1;
        }
        //如果传的当前页大于最大分页，设置成最大分页
        if(curPage>totalPages&&totalPages!=0){
            curPage=totalPages;
        }
        //计算开始行
        int  startRows = (curPage-1)*pageSize;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startRows",startRows);
        paramMap.put("pageSize",pageSize);
        List<ActivitiesInfo>  ActivitiesInfos=activitiesService.getAllActivitiesInfos(paramMap);
        Calendar calendar=Calendar.getInstance();
        //遍历查询到的数据，将结束时间传入对象中
        for (int i=0; i<ActivitiesInfos.size();i++){
            ActivitiesInfo activitiesInfo = ActivitiesInfos.get(i);
            calendar.setTime(activitiesInfo.getStarttime());
            calendar.add(Calendar.HOUR,activitiesInfo.getDuration());
            activitiesInfo.setEndTime(calendar.getTime());
        }
        //向页面传数据
        model.addAttribute("ActivitiesInfos",ActivitiesInfos);
        model.addAttribute("curPage",curPage);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("date",date);
        //跳转模板页面
        return "index";

    }
    /**
     * 跳转到新增
     * @return
     */
    @RequestMapping("/addActivity")
    public  String toAddActivity(Model model){
        model.addAttribute("title", "新增");
        return  "addActivity";
    }

    /**
     * 接收form表单内容(添加或者修改)
     * @return
     */
    @RequestMapping("/activity/addActivity")
    public  String addActivity(String id,String type,String starttime,int duration,String site,int peoples,String note ){
        //获取uuid作为id
        String uuid=UUID.randomUUID().toString().replace("-", "");
        //修改日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startTime=null;
        try {
            starttime=starttime.replace("T"," ");
            startTime = dateFormat.parse(starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //实例化对象并传值
        ActivitiesInfo activitiesInfo= new ActivitiesInfo();
        activitiesInfo.setType(type);
        activitiesInfo.setStarttime(startTime);
        activitiesInfo.setDuration(duration);
        activitiesInfo.setSite(site);
        activitiesInfo.setPeoples(peoples);
        activitiesInfo.setNote(note);
        //如果传的id是null，则进行添加操作
        if(""==id){
            activitiesInfo.setId(uuid);
            activitiesService.addActivity(activitiesInfo);
            //否则进行修改操作
      } else{
            activitiesInfo.setId(id);
            activitiesService.updateActivity(activitiesInfo);
        }
        //重定向到index页面
        return  "redirect:/index";
    }

    /**
     * 跳转到更新页面
     * @param id
     * @return
     */
    @RequestMapping("/toUpdate")
    public  String  toUpdate(Model model,
                             @RequestParam("id") String id){
        //根据id的获取活动信息
        ActivitiesInfo activitiesInfo = activitiesService.getActvityById(id);
        model.addAttribute("title", "修改");
        model.addAttribute("activitiesInfo",activitiesInfo);
        //跳转到更新页面，更新页面和增加页面共用一个页面
        return  "addActivity";
    }
    @RequestMapping("/delete")
    public String deleteActivity(@RequestParam("id") String id){
        activitiesService.deleteActivity(id);
        //重定向到index页面
        return  "redirect:/index";
    }


 }

