package com.example.pjjsp241022.controller;

import com.example.pjjsp241022.dto.Board;
import com.example.pjjsp241022.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final BoardService service;

    // 게시물 CRUD

    // /board/new
    @GetMapping("new")
    public void newBoard() {

        // /WEB-INF/view/board/new.jsp
    }

    @PostMapping("new")
    public String newBoard(Board board, RedirectAttributes rttr) {
        service.add(board);

        rttr.addFlashAttribute("message", Map.of("type", "success",
                "text", "새 게시물이 등록되었습니다."));
        rttr.addAttribute("id", board.getId());
        return "redirect:/board/view";
    }

    // /board/list
    // /board/list?page=1
    @GetMapping("list")
    public void listBoard(@RequestParam(name = "page", defaultValue = "1") Integer page,
                          Model model) {
        // 해당 페이지에 맞는 레코드 리스트 가져오기
        List<Board> list = service.list(page);
        model.addAttribute("boardList", list);

        // 페이지 당 보여줄 레코드 수
        int recordsPerPage = 10;

        // 총 레코드 수
        Integer numberOfRows = service.getTotalRecordCount();

        // 총 페이지 수 (총 레코드 수 / 페이지당 레코드 수)
        Integer totalPages = (int) Math.ceil((double) numberOfRows / recordsPerPage);

        // 현재 페이지
        Integer currentPage = page;

        // 페이지 그룹의 끝 페이지 (1~10 페이지, 11~20 페이지 등의 그룹으로 나누기)
        Integer endPage = (int) (Math.ceil((double) currentPage / 10)) * 10;

        // 페이지 그룹의 시작 페이지
        Integer beginPage = endPage - 9;

        // 총 페이지 수가 현재 endPage보다 작으면 마지막 페이지로 endPage를 설정
        if (endPage > totalPages) {
            endPage = totalPages;
        }

        // 이전 페이지 그룹
        Integer prevPage = (beginPage > 1) ? beginPage - 1 : 1;

        // 다음 페이지 그룹
        Integer nextPage = (endPage < totalPages) ? endPage + 1 : totalPages;

        // 첫 페이지
        Integer firstPage = 1;

        // 마지막 페이지
        Integer lastPage = totalPages;

        // 모델에 추가
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("beginPage", beginPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);
    }

    @GetMapping("view")
    public void viewBoard(Integer id, Model model) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("delete")
    public String deleteBoard(Integer id, RedirectAttributes rttr) {
        service.remove(id);

        rttr.addFlashAttribute("message", Map.of("type", "warning",
                "text", "게시물이 삭제되었습니다."));
        return "redirect:/board/list";
    }

    @GetMapping("edit")
    public void editBoard(Integer id, Model model) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("edit")
    public String editBoard(Board board, RedirectAttributes rttr) {

        service.update(board);

        rttr.addFlashAttribute("message", Map.of("type", "edited",
                "text", "게시물이 수정되었습니다."));
        rttr.addAttribute("id", board.getId());
        return "redirect:/board/view";
    }
}
