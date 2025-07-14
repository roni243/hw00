package shop.mtcoding.blog.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @PostMapping("/board/save")
    public String boardsave(String title, String content){
        boardNativeRepository.save(title, content);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String boardList(Model model) {
        List<Board> boards = boardNativeRepository.findAll();
        model.addAttribute("models", boards);
        return "board/list";
    }

    @GetMapping("/board/save-form")
    public String boardSaveForm(){
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable("id") int id, Model model){
        BoardResponse.DTO board = boardNativeRepository.findById(id);
        model.addAttribute("model", board);
        return "board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String boardUpdateForm(@PathVariable("id") int id, Model model) {
        BoardResponse.DTO board = boardNativeRepository.findById(id);

        model.addAttribute("id", board.getId());
        model.addAttribute("title", board.getTitle());
        model.addAttribute("content", board.getContent());

        return "board/update-form";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") int id, String title, String content, Model model){
        boardNativeRepository.update(id, title, content);
        return "redirect:/board";
    }

    @PostMapping("/board/{id}/delete")
    public String boardDelete(@PathVariable("id") int id) {
        boardNativeRepository.deleteById(id);
        return "redirect:/board";
    }
}
