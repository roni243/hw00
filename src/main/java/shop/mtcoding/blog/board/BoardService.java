package shop.mtcoding.blog.board;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardNativeRepository boardNativeRepository;


    public void save(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        Board board = saveDTO.toEntity(sessionUser);
        boardNativeRepository.save(board);
    }

    public void delete(int id, User sessionUser) {
        BoardResponse.DTO board;
        try{
            board = boardNativeRepository.findById(id);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }

        if (!sessionUser.getId().equals(board.getUserId())) {
            throw new IllegalArgumentException("본인 게시글만 삭제할 수 있습니다.");
        }

        boardNativeRepository.deleteById(id);

    }
}
