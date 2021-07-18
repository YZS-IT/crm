package com.yzs.crm.web.listener;

import com.yzs.crm.settings.pojo.DicValue;
import com.yzs.crm.settings.service.IDicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;

public class ContextListener implements ServletContextListener {

    /*
    * 这是一个 Servlet容器上下文对象 监听器 , 当服务器启动,Servlet容器启动后
    * 马上执行该方法
    *
    * event :   该参数可以获取到监听到的对象.
    * */

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //获取到Servlet容器上下文对象
        ServletContext application = sce.getServletContext();
        //获取到Spring容器上下文对象
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
        //获取到DicService对象,调用其服务,将返回结果存储在application域中
        IDicService dicService = (IDicService) ctx.getBean("IDicServiceImpl");
        Map<String , List<DicValue>> map = dicService.getAll();
        for (String s : map.keySet()) {
            application.setAttribute(s,map.get(s));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
