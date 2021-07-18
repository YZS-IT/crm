package com.yzs.crm.workbench.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzs.crm.settings.dao.IUserDao;
import com.yzs.crm.settings.pojo.User;
import com.yzs.crm.settings.service.IUserService;
import com.yzs.crm.util.DateTimeUtil;
import com.yzs.crm.util.UUIDUtil;
import com.yzs.crm.workbench.bo.ClueValue;
import com.yzs.crm.workbench.dao.IClueDao;
import com.yzs.crm.workbench.pojo.Activity;
import com.yzs.crm.workbench.pojo.Clue;
import com.yzs.crm.workbench.service.IClueService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workbench/clue")
public class ClueController {

    @Resource
    private IClueService clueService;

    @Resource
    private IUserService userService;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        return userService.getUserList();
    }

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


    @RequestMapping("/detail.do")
    public ModelAndView detail(@RequestParam("id") String id,ModelAndView mv){
        mv.addObject("c",clueService.detail(id));
        mv.setViewName("forward:/workbench/clue/detail.jsp");
        return mv;
    }

}
