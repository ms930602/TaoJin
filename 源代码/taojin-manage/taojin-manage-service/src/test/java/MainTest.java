import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author 蒙赛
 * @Date 2018/11/24 14:46
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext-service-test.xml"}) //加载配置文件
public class MainTest {

    @Before
    public void setUp(){

    }

    @Test
    public void test() {

    }
}
