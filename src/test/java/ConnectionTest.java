import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"applicationContext.xml"}
)
public class ConnectionTest {

    @Autowired
    DataSource ds;

    @Autowired
    SqlSession sqlsession;

    @Test
    public void connectionTest(){
        System.out.println(ds);

    }
}
