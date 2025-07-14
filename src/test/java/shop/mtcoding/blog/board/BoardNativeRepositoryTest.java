package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

    @Autowired
    private EntityManager em;

    @Test
    public void save_test() {

        // 저장 전
        List<Board> beforeList = boardNativeRepository.findAll();
        System.out.println("저장 전 게시글 수: " + beforeList.size());

        //given
        String title = "제목6";
        String content = "내용6";

        //when
        boardNativeRepository.save(title, content);

        // 저장 후
        List<Board> afterList = boardNativeRepository.findAll();
        System.out.println("저장 후 게시글 수: " + afterList.size());

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
        BoardResponse.DTO board = boardNativeRepository.findById(id);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void deleteById_test() {
        int id = 1;
        // 삭제 전 데이터 조회
        try {
            BoardResponse.DTO beforeDelete = boardNativeRepository.findById(id);
            System.out.println("삭제 전: " + beforeDelete);
        } catch (NoResultException e) {
            System.out.println("삭제 전: 해당 ID의 게시물이 없습니다.");
        }

        // 삭제 수행
        boardNativeRepository.deleteById(id);
        System.out.println("삭제 수행 완료");

        // 삭제 후 데이터 조회
        try {
            BoardResponse.DTO afterDelete = boardNativeRepository.findById(id);
            System.out.println("삭제 후: " + afterDelete);
        } catch (NoResultException e) {
            System.out.println("삭제 후: 해당 ID의 게시물이 삭제되었습니다.");
        }


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
