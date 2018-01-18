import com.wangli.pojo.User;
import com.wangli.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMybatis {
    private static Logger logger = Logger.getLogger(TestMybatis.class);

    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User("Jack", 22);
        System.out.println(userService.addUser(user));
    }

    @Test
    public void testGet() {
        System.out.println(userService.getUserById(1));
    }
}
