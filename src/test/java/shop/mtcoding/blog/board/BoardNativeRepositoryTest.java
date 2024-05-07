package shop.mtcoding.blog.board;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 1. BoardNativeRepository 를 전체 프로젝트를 실행하지 않고 테스트하기 위한 클래스.
 * 2. 전체 코드를 다 작성하고 프로젝트를 실행하면서 DB 관련 코드를 테스트하는 것은 번거롭고 시간이 오래 걸림.
 * 3. 필요한 부분만 메모리에 올려서 테스트하는 것을 단위 테스트라고 함.
 * 4. 단위 테스트에서는 각 메서드가 종료될 때마다 rollback 이 됨.
 * 5. 단위 테스트시에 각 메서드는 서로 의존 하면 좋지 않음. 때문에 순서에 영향을 받지 않고 테스트가 가능해야 함.
 * 6. TDD(테스트주도개발) 책에 보면 테스트는 완전히 독립적인 것이 좋다고 나와있음.
 * 7. 아래 예제에서는 순서를 지정해서 테스트함. 이유는 네번째 테스트에서 1번 게시글을 삭제해도 다섯번째 테스트에서 1번 게시글을 수정할 수 있음.
 *    이를 통해 rollback이 된다는 것을 알 수 있기 때문에 학습(학생들이 공부하기)에 효과가 있음.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 테스트 순서를 정하기 위한 세팅
@Import(BoardNativeRepository.class) // 내가 만든 해당 객체를 테스트 전용 IoC에 로드
@DataJpaTest // DB 관련 모든 객체를 IoC에 로드
public class BoardNativeRepositoryTest {

    @Autowired // DI
    private BoardNativeRepository boardNativeRepository;

    /**
     * 다섯번째 테스트 - 게시글 수정하기
     */
    @Order(5)
    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목수정1";
        String content = "내용수정1";

        // when
        boardNativeRepository.updateById(id, title, content);

        // eye
        Board board = boardNativeRepository.findById(id);
        System.out.println("5. updateById_test/board : " + board);

        // then (org.assertj.core.api)
        assertThat(board.getTitle()).isEqualTo("제목수정1");
        assertThat(board.getContent()).isEqualTo("내용수정1");
    } // rollback

    /**
     * 네번째 테스트 - 게시글 삭제하기
     */
    @Order(4)
    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardNativeRepository.deleteById(id);

        // eye
        List<Board> boardList = boardNativeRepository.findAll();
        System.out.println("4. deleteById_test/board : " + boardList.size());

        // then (org.assertj.core.api)
        assertThat(boardList.size()).isEqualTo(4);
    } // rollback

    /**
     * 세번째 테스트 - 게시글 등록하기
     */
    @Order(3)
    @Test
    public void save_test(){
        // given
        String title = "제목6";
        String content = "내용6";

        // when
        boardNativeRepository.save(title, content);

        // eye
        Board board = boardNativeRepository.findById(6);
        System.out.println("3. save_test/board : " + board);

        // then
        // then (org.assertj.core.api)
        assertThat(board.getTitle()).isEqualTo("제목6");
        assertThat(board.getContent()).isEqualTo("내용6");
    } // rollback

    /**
     * 두번째 테스트 - 게시글 상세보기
     */
    @Order(2)
    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardNativeRepository.findById(id);

        // eye
        System.out.println("2. findById_test/board : " + board);

        // then (org.assertj.core.api)
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
    } // rollback

    /**
     * 첫번째 테스트 - 게시글 목록보기
     */
    @Order(1)
    @Test
    public void findAll_test() {
        // given


        // when
        List<Board> boardList = boardNativeRepository.findAll();

        // eye
        System.out.println("1. findAll_test/size : " + boardList.size());

        // then (org.assertj.core.api)
        assertThat(boardList.size()).isEqualTo(5);
    } // rollback
}
