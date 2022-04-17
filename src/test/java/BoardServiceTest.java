import com.boardPractice.domain.BoardVO;
import com.boardPractice.persistence.BoardDAO;
import com.boardPractice.service.BoardService;
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
public class BoardServiceTest {
    @Autowired
    BoardDAO dao;

    @Autowired
    BoardService service;

    @Test
    public void test()throws Exception{
        BoardVO boardVO =dao.read(3538932);
        System.out.println("boardVO = " + boardVO);
    }

    @Test
    public void listTest() throws Exception{
        BoardVO boardVO = new BoardVO(1, 10);

        List<BoardVO> list = service.list(boardVO);

        System.out.println(list);
    }

    @Test
    public void updateTest() throws Exception{

        BoardVO boardVO = new BoardVO(1, 10);
        boardVO.setAction("MOD");
        boardVO.setBno(1);
        service.proc(boardVO);

    }

    @Test
    public void createParentTest() throws Exception{
        BoardVO vo = new BoardVO(1, 10);
        vo.setTitle("3번째 게시물이요");
        vo.setContent("3번째 게시물이요");
        vo.setAction("WRT");
        service.proc(vo);
    }

    @Test
    public void createChildTest() throws Exception{
        BoardVO vo = new BoardVO(1, 10);
        vo.setTitle("1번 게시물 답글2의 답글 2의 답글 1이요");
        vo.setContent("1번 게시물 답글2의 답글 2의 답글 1이요");
        vo.setAction("WRT");
        vo.setReply(true);
        vo.setParentBno(8);
        service.proc(vo);
    }

    @Test
    public void deleteTest() throws Exception{
        int result = dao.delete(3);
        System.out.println(result);
    }
}
