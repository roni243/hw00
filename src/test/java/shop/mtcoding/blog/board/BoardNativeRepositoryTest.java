package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {

    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void save_test() {

        //given
        String title = "제목6";
        String content = "내용6";

        //when
        boardNativeRepository.save(title, content);

    }

    @Test
    public void findAll_test() {
        List<Board> boards = boardNativeRepository.findAll();
        Assertions.assertThat(boards.get(0).getId()).isEqualTo(5);
        Assertions.assertThat(boards.get(0).getTitle()).isEqualTo("제목5");
        Assertions.assertThat(boards.get(0).getContent()).isEqualTo("내용5");
    }

    @Test
    public void findById_test() {
        int id = 1;
        Board board = boardNativeRepository.findById(id);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void deleteById_test() {
        int id = 1;
        boardNativeRepository.deleteById(id);
    }

    @Test
    public void update_test() {
        // given
        int id = 1;
        String newTitle = "수정된 제목";
        String newContent = "수정된 내용";

        // when
        boardNativeRepository.update(id, newTitle, newContent);
        System.out.println(id+"번 게시글");
        System.out.println(newTitle);
        System.out.println(newContent);
    }
}
