package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardNativeRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public void save (String title, String content) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, created_at) values(?, ?, now())");
        query.setParameter(1, title);
        query.setParameter(2, content);

        query.executeUpdate();
    }

    @Transactional
    public void update(int id, String title, String content) {
        Query query = em.createNativeQuery("UPDATE board_tb SET title = ?, content = ? WHERE id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate();
    }


    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc");
        List<Object[]> rows = query.getResultList();

        List<Board> boards = new ArrayList<>();
        for (Object[] row : rows) {
            Board board = new Board(
                    ((Number) row[0]).intValue(),   // id
                    (String) row[1],                // title
                    (String) row[2],                // content
                    (Timestamp) row[3]// created_at
            );
            boards.add(board);
        }

        return boards;
    }

    public Board findById(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class);

        query.setParameter(1, id);

        return (Board) query.getSingleResult();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }


}
