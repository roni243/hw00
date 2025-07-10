package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Table(name = "board_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Integer contentLength;

    public Board(Integer id, String title, String content, Timestamp createdAt, Integer contentLength) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.contentLength = contentLength;
    }
}
