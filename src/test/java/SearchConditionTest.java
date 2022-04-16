//import com.boardPractice.domain.BoardVO;
//import com.boardPractice.domain.PageMaker;
//import com.boardPractice.domain.SearchCondition;
//import com.boardPractice.persistence.BoardDAO;
//import com.boardPractice.service.BoardService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//        locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"}
//)
//public class SearchConditionTest {
//
//    @Autowired
//    BoardDAO dao;
//
//    @Autowired
//    BoardService service;
//
//    @Test
//    public void dummyData()throws Exception{
//        dao.deleteAll();
//        for(int i = 0; i < 230;i++){
//            BoardVO vo = new BoardVO("title" + i, "content" + i, "yeop");
//            dao.create(vo);
//        }
//
//    }
//
//
//    @Test
//    public void listTest(){
//        SearchCondition sc = new SearchCondition(2, 10, "C", "content1");
//        try{
//            PageMaker pm = new PageMaker(sc, dao.conditionListCnt(sc));
//            int cnt = dao.conditionListCnt(sc);
//            System.out.println("cnt = " + cnt);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}
