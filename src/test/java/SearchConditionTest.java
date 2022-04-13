import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;
import com.boardPractice.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"}
)
public class SearchConditionTest {
    
    @Autowired
    BoardDAO dao;
    
    @Test
    public void listTest(){
        SearchCondition sc = new SearchCondition(2, 10, "C", "content1");
        try{
            PageMaker pm = new PageMaker(sc, dao.conditionListCnt(sc));
            int cnt = dao.conditionListCnt(sc);
            System.out.println("cnt = " + cnt);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
