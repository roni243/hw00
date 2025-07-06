package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.sql.Timestamp;

@Data
@Table(name = "board_tb")
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Timestamp createdAt;
}
