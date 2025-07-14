package shop.mtcoding.blog.board;

import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    @Data
    public static class DTO {
        public int id;
        public String title;
        public String content;
        public Timestamp created;
        public Integer contentLength;

        public DTO(int id, String title, String content, Timestamp created, Integer contentLength) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.created = created;
            this.contentLength = contentLength;
        }
    }
}
