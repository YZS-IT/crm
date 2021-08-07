package com.yzs.crm.workbench.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yzs.crm.settings.pojo.User;
import com.yzs.crm.settings.service.IUserService;
import com.yzs.crm.util.DateTimeUtil;
import com.yzs.crm.util.UUIDUtil;
import com.yzs.crm.workbench.bo.ClueValue;
import com.yzs.crm.workbench.pojo.*;
import com.yzs.crm.workbench.service.IActivityService;
import com.yzs.crm.workbench.service.IClueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workbench/clue")
public class ClueController {

    @Resource
    private IClueService clueService;

    @Resource
    private IActivityService activityService;

    @Resource
    private IUserService userService;

    //获取所有者列表
    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        return userService.getUserList();
    }

    //获取线索列表
    @RequestMapping("/queryClue.do")
    @ResponseBody
    public Map<String, Object> queryClue(ClueValue clueValue, Integer pageNum, Integer pageSize){

        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(pageNum,pageSize);
        List<Clue> clueList = clueService.queryClue(clueValue);
        PageInfo<Clue> cluePageInfo = new PageInfo<>(clueList);

        map.put("clueList",clueList);
        map.put("total",cluePageInfo.getTotal());
        map.put("pages",cluePageInfo.getPages());

        return map;
    }

    //点击线索名称,返回线索详情页面
    @RequestMapping("/detail.do")
    public ModelAndView detail(@RequestParam("id") String id,ModelAndView mv){

        mv.addObject("c",clueService.detail(id));
        List<ClueActivityRelation> clueActivityRelationList = clueService.getClueActivityRelationList(id);
        List<Activity> activityList = new ArrayList<>();
        for (ClueActivityRelation clueActivityRelation : clueActivityRelationList) {
            Activity activity = activityService.detail(clueActivityRelation.getActivityId());
            activity.setId(clueActivityRelation.getId());
            activityList.add(activity);
        }
        mv.addObject("activityList",activityList);
        mv.setViewName("forward:/workbench/clue/detail.jsp");
        return mv;
    }

    //解除关联
    @RequestMapping("/unbind.do")
    @ResponseBody
    public Map<String, Object> unbind(@RequestParam("id") String id){
        Map<String, Object> map = new HashMap<>();
        boolean flag = clueService.deleteClueActivityRelation(id);
        map.put("success",flag);
        return map;
    }
    //添加关联
    @RequestMapping("/bind.do")
    @ResponseBody
    public Map<String, Object> bind(@RequestParam("cid") String clueId, @RequestParam("aid") List<String> activityIds){
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        for (String activityId : activityIds) {
            ClueActivityRelation relation = new ClueActivityRelation();
            relation.setClueId(clueId);
            relation.setActivityId(activityId);
            relation.setId(UUIDUtil.getUUID());
            if (!clueService.saveClueActivityRelation(relation)){
                flag = false;
                break;
            }
        }
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/getActivityListLikeNameAndNotBeBindedByClueId.do")
    @ResponseBody
    public Map<String, Object> getActivityListLikeName(String name, String clueId){
        Map<String, Object> map = new HashMap<>();
        List<Activity> activityList = clueService.getActivityLikeNameAndNotBoundByClueId(name,clueId);
        map.put("data",activityList);
        return map;
    }

    //用户执行添加操作后,返回操作结果
    @RequestMapping("/save.do")
    @ResponseBody
    public Map<String, Object> save(Clue clue, HttpSession session){
        Map<String, Object> map = new HashMap<>();

        User user = (User) session.getAttribute("user");

        clue.setCreateBy(user.getName());
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());

        boolean flag = clueService.save(clue);

        map.put("success",flag);

        return map;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("id") List<String> ids){
        Map<String, Object> map = new HashMap<>();
        boolean flag = clueService.delete(ids);
        map.put("success",flag);
        return map;
    }

    //
    @RequestMapping("/edit.do")
    @ResponseBody
    public Map<String, Object> edit(@RequestParam("id") String id){
        Map<String, Object> map = new HashMap<>();
        map.put("clue",clueService.findById(id));
        map.put("userList",userService.getUserList());
        return map;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public Map<String, Object> update(HttpSession session, Clue clue){
        User user =(User) session.getAttribute("user");

        Map<String, Object> map = new HashMap<>();

        Clue oleClue = clueService.findById(clue.getId());

        clue.setCreateTime(oleClue.getCreateTime());
        clue.setCreateBy(oleClue.getCreateBy());

        clue.setEditBy(user.getName());
        clue.setEditTime(DateTimeUtil.getSysTime());

        map.put("success",clueService.update(clue));

        return map;
    }

    @RequestMapping("/convert.do")
    //SpringMVC框架会 自动 将已经接收的用户提交参数 存储在 request域 中,再进行转发.
    // 还有一种,是直接在jsp页面中用EL的内置对象param直接用.
    public String convert(Clue clue){
        return "forward:/workbench/clue/convert.jsp";
    }

    @RequestMapping("/convertConfirm.do")
    public Map<String ,Object> convertConfirm(HttpSession session,
                                              @RequestParam("clueId") String clueId,
                                              @RequestParam("flag") Boolean flag,
                                              String tranMoney, String tranName, String tranExpectedDate,String tranStage, String tranActivityId){

        User user = (User)session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        Clue clue = clueService.findById(clueId);
        Customer customer = new Customer();
        customer.setId(UUIDUtil.getUUID());
        customer.setOwner(clue.getOwner());
        customer.setName(clue.getCompany());
        customer.setWebsite(clue.getWebsite());
        customer.setPhone(clue.getPhone());
        customer.setDescription(clue.getDescription());
        customer.setCreateBy(user.getName());
        customer.setCreateTime(DateTimeUtil.getSysTime());
        customer.setContactSummary(clue.getContactSummary());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setAddress(clue.getAddress());


        Contact contact = new Contact();
        contact.setId(UUIDUtil.getUUID());
        contact.setOwner(clue.getOwner());
        contact.setSource(clue.getSource());
        contact.setCustomerId(customer.getId());
        contact.setFullname(clue.getFullname());
        contact.setAppellation(clue.getAppellation());
        contact.setEmail(clue.getEmail());
        contact.setMphone(clue.getMphone());
        contact.setJob(clue.getJob());
        contact.setCreateBy(user.getName());
        contact.setCreateTime(DateTimeUtil.getSysTime());

        return map;
    }
}
