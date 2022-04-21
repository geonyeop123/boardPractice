import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.BoardVO;
import com.boardPractice.persistence.BoardDAO;
import com.boardPractice.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"}
)
public class BoardServiceTest {
    @Autowired
    BoardDAO dao;

    @Autowired
    BoardService service;

    @Test
    public void listTest() throws Exception{
        BoardVO vo = new BoardVO(1, 10);
        vo = service.list(vo);
        System.out.println(vo);
    }
    @Test
    public void deleteTest() throws Exception{
        int result = dao.delete(2);
        System.out.println(result);
    }

    @Test
    public void createTest() throws Exception{
        BoardVO vo = new BoardVO(1, 10);
        BoardDTO dto = new BoardDTO("title", "content", "yeop");
        vo.setBoardDTO(dto);
        vo.setAction("WRT");
        System.out.println(vo);

        service.proc(vo);
    }

    @Test
    public void createTest2() throws Exception{
        BoardVO vo = new BoardVO(1, 10);
        BoardDTO dto = new BoardDTO("title", "content", "yeop");
        vo.setBoardDTO(dto);
        vo.setAction("REP");
        vo.setBno(27);
        System.out.println(vo);
        service.proc(vo);
    }

    @Test
    public void deleteTest1() throws Exception{
        BoardVO vo = new BoardVO(1, 10);
        BoardDTO dto = new BoardDTO("hi","d","yeop");
        vo.setBoardDTO(dto);
        System.out.println("vo = " + vo);
        vo.setBno(2);
        vo.setAction("DEL");
        service.proc(vo);
    }
}
