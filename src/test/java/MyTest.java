import com.yzs.crm.settings.service.IDicService;
import com.yzs.crm.util.MD5Util;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test01(){
        System.out.println(MD5Util.getMD5("123"));
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
//        IUserService userService = (IUserService) ac.getBean("IUserServiceImpl");
//        try {
//            userService.login("zs","123","127.0.0.1");
//        } catch (LoginException e) {
//            e.printStackTrace();
//        }

//        IActivityService activityService = (IActivityService) ac.getBean("IActivityServiceImpl");

//        activityService.findAll("三","","","");

//        activityService.findRemarkById("6430a2a1c38c487ba5f323c344ffdd11");

//        IDicService dicService = (IDicService) ac.getBean("IDicServiceImpl");
//        DicValue dicValue = new DicValue();
//        dicValue.setId(UUIDUtil.getUUID());
//        dicValue.setValue("40");
//        dicValue.setText("40");
//        dicValue.setOrderNo("0");
//        dicValue.setTypeCode("pageSize");
//        dicService.insert(dicValue);

//        IClueDao clueDao = (IClueDao) ac.getBean("IClueDao");
//        clueDao.findAll(null);

        IDicService dicService = (IDicService) ac.getBean("IDicServiceImpl");


    }

}

