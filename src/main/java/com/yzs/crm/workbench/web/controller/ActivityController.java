package com.yzs.crm.workbench.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzs.crm.settings.pojo.User;
import com.yzs.crm.settings.service.IUserService;
import com.yzs.crm.util.DateTimeUtil;
import com.yzs.crm.util.UUIDUtil;
import com.yzs.crm.workbench.pojo.Activity;
import com.yzs.crm.workbench.service.IActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {

    @Resource
    private IActivityService activityService;

    @Resource
    private IUserService userService;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        List<User> userList = userService.getUserList();
        return userList;
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public Map<String, Object> save(Activity activity, HttpSession session){

        User user = (User) session.getAttribute("user");

        activity.setCreateBy(user.getName());
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());

        boolean flag = activityService.save(activity);

        Map<String, Object> map = new HashMap<>();

        map.put("success",flag);

        return map;
    }

    @RequestMapping("/findAll.do")
    @ResponseBody
    public Map<String, Object> findAll(@RequestParam(value = "pageNum") Integer pageNum,
                                       @RequestParam(value = "pageSize") Integer pageSize,
                                       @RequestParam(value = "owner", required = false) String owner,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "startDate", required = false) String startDate,
                                       @RequestParam(value = "endDate", required = false) String endDate){

        System.out.println(owner+","+name+","+startDate+","+endDate+",");
        PageHelper.startPage(pageNum,pageSize);
        List<Activity> activityList = activityService.findAll(owner,name,startDate,endDate);
        PageInfo<Activity> activityPageInfo = new PageInfo<>(activityList);
        Map<String, Object> map = new HashMap<>();
        map.put("data",activityList);
        map.put("total",activityPageInfo.getTotal());
        return map;
    }

    @RequestMapping("/getActivityAndUserList.do")
    @ResponseBody
    public Map<String, Object> getActivityAndUserList(@RequestParam(value = "id") String id){
        Map<String, Object> map = new HashMap<>();
        map.put("activity",activityService.getActivity(id));
        map.put("userList",userService.getUserList());
        return map;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public Map<String, Object> update(Activity activity, HttpSession session){
        User user = (User) session.getAttribute("user");

        activity.setEditBy(user.getName());
        activity.setCreateTime(DateTimeUtil.getSysTime());

        boolean flag = activityService.update(activity);

        Map<String, Object> map = new HashMap<>();
        map.put("success",flag);
        return map;

    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("id") List<String> ids){
        boolean flag = activityService.delete(ids);
        Map<String, Object> map = new HashMap<>();
        map.put("success",flag);
        return map;
    }

}
