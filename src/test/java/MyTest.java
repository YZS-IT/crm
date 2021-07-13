import com.yzs.crm.settings.exception.LoginException;
import com.yzs.crm.settings.service.IUserService;
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
        IUserService userService = (IUserService) ac.getBean("IUserServiceImpl");
        try {
            userService.login("zs","123","127.0.0.1");
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
