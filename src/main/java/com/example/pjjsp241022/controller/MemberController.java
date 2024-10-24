package com.example.pjjsp241022.controller;

import com.example.pjjsp241022.dto.Member;
import com.example.pjjsp241022.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;


    @GetMapping("signup")
    public void signup() {

    }

    @PostMapping("signup")
    public String signupProcess(Member member, RedirectAttributes rttr) {

        service.addMember(member);

        rttr.addFlashAttribute("message", Map.of("type", "success",
                "text", "회원가입 되었습니다."));

        return "redirect:/member/list";
    }

    @GetMapping("list")
    public void list(Model model) {
        model.addAttribute("memberList", service.list());

    }

    @GetMapping("view")
    public void info(String id, Model model) {
        Member member = service.info(id);
        model.addAttribute("member", member);
    }

    @PostMapping("delete")
    public String deleteMember(String id, String password, RedirectAttributes rttr) {

        if (service.remove(id, password)) {
            // 탈퇴 성공
            rttr.addFlashAttribute("message", Map.of(
                    "type", "warning",
                    "text", "탈퇴가 완료되었습니다."));
            return "redirect:/member/signup";
        } else {
            // 탈퇴 실패
            rttr.addFlashAttribute("message", Map.of(
                    "type", "danger",
                    "text", "비밀번호가 일치하지 않습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/member/view";
        }
    }

    @GetMapping("edit")
    public void editMember(String id, Model model) {
        Member member = service.get(id);
        model.addAttribute("member", member);
    }

    @PostMapping("edit")
    public String editProcess(String id, RedirectAttributes rttr) {
        service.update(id);

        rttr.addFlashAttribute("massage", Map.of(
                "type", "info",
                "text", "정보가 수정되었습니다."));
        return "redirect:/member/view";
    }

}
