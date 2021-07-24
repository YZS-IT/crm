package com.yzs.crm.settings.web.controller;

import com.yzs.crm.settings.pojo.DicType;
import com.yzs.crm.settings.pojo.DicValue;
import com.yzs.crm.settings.service.IDicService;
import com.yzs.crm.util.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/settings/dictionary")
public class DictionaryController {

    @Resource
    private IDicService dicService;

    //数据字典类型

    @RequestMapping("/type/index.do")
    public ModelAndView typeIndex(ModelAndView mv){
        mv.addObject("typeList",dicService.getTypeList());
        mv.setViewName("forward:/settings/dictionary/type/index.jsp");
        return mv;
    }

    @RequestMapping("/type/delete.do")
    @ResponseBody
    public Map<String ,Object> typeDelete(@RequestParam("id") List<String> ids){
        Map<String ,Object> map = new HashMap<>();
        boolean flag = dicService.deleteType(ids);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/type/edit.do")
    public ModelAndView typeEdit(ModelAndView mv,@RequestParam("id") String id){
        mv.addObject("t",dicService.getTypeById(id));
        mv.setViewName("forward:/settings/dictionary/type/edit.jsp");
        return mv;
    }

    @RequestMapping("/type/update.do")
    @ResponseBody
    public Map<String ,Object> typeUpdate(DicType dicType){
        Map<String ,Object> map = new HashMap<>();
        boolean flag = dicService.updateType(dicType);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/type/save.do")
    @ResponseBody
    public Map<String ,Object> typeSave(DicType dicType){
        dicType.setId(UUIDUtil.getUUID());
        Map<String ,Object> map = new HashMap<>();
        boolean flag = dicService.saveType(dicType);
        map.put("success",flag);
        return map;
    }

    //数据字典值

    @RequestMapping("/value/index.do")
    public ModelAndView valueIndex(ModelAndView mv){
        mv.addObject("valueList",dicService.getValueList());
        mv.setViewName("forward:/settings/dictionary/value/index.jsp");
        return mv;
    }

    @RequestMapping("/value/delete.do")
    @ResponseBody
    public Map<String ,Object> valueDelete(@RequestParam("id") List<String> ids){
        Map<String ,Object> map = new HashMap<>();
        boolean flag = dicService.deleteValue(ids);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/value/edit.do")
    public ModelAndView valueEdit(ModelAndView mv,@RequestParam("id") String id){
        mv.addObject("v",dicService.getValueById(id));
        mv.setViewName("forward:/settings/dictionary/value/edit.jsp");
        return mv;
    }

    @RequestMapping("/value/update.do")
    @ResponseBody
    public Map<String ,Object> valueUpdate(DicValue dicValue){
        Map<String ,Object> map = new HashMap<>();
        boolean flag = dicService.updateValue(dicValue);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/value/save.do")
    @ResponseBody
    public Map<String ,Object> ValueSave(DicValue dicValue){
        dicValue.setId(UUIDUtil.getUUID());
        Map<String ,Object> map = new HashMap<>();
        boolean flag = dicService.saveValue(dicValue);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/value/getTypeCodeList.do")
    @ResponseBody
    public Map<String ,Object> getTypeCodeList(){
        Map<String ,Object> map = new HashMap<>();
        map.put("data",dicService.getTypeList());
        return map;
    }
}
