package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

public class BoardResponse {

    @Data
    public static class DTO {
        public int id;
        public String title;
        public String content;
        public Timestamp created;
        public Integer contentLength;
        public Integer userId;
        public String username;

        public DTO(int id, String title, String content, Timestamp created, Integer contentLength, Integer userId, String username) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.created = created;
            this.contentLength = contentLength;
            this.userId = userId;
            this.username = username;
        }
    }
}
