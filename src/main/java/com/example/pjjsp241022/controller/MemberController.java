package com.example.pjjsp241022.controller;

import com.example.pjjsp241022.dto.Member;
import com.example.pjjsp241022.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
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
    public void edit(String id, Model model) {
        model.addAttribute("member", service.info(id));
    }

    @PostMapping("edit")
    public String editProcess(Member member, RedirectAttributes rttr) {
        try {
            service.update(member);
            rttr.addFlashAttribute("message", Map.of("type", "success",
                    "text", "회원정보가 수정되었습니다."));

        } catch (DuplicateKeyException e) {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", STR."\{member.getNickName()}은 이미 사용중인 별명입니다."));

            rttr.addAttribute("id", member.getId());
            return "redirect:/member/edit";
        }

        rttr.addAttribute("id", member.getId());
        return "redirect:/member/view";
    }

    @GetMapping("edit-password")
    public String editPassword(String id, Model model) {
        model.addAttribute("id", id);

        return "/member/editPassword";
    }

    @PostMapping("edit-password")
    public String editPasswordProcess(String id,
                                      String oldPassword,
                                      String newPassword,
                                      RedirectAttributes rttr) {
        if (service.updatePassword(id, oldPassword, newPassword)) {
            rttr.addFlashAttribute("message", Map.of(
                    "type", "success",
                    "text", "암호가 변경되었습니다."));
            return "redirect:/member/view";
        } else {
            rttr.addFlashAttribute("message", Map.of(
                    "type", "danger",
                    "text", "암호가 일치하지 않습니다."));
            return "redirect:/member/edit-password";
        }
    }

    @GetMapping("login")
    public void login() {

    }

    @PostMapping("login")
    public String loginProcess(String id, String password,
                               RedirectAttributes rttr,
                               HttpSession session) {
        Member member = service.get(id, password);

        if (member == null) {
            // 로그인 실패
            rttr.addFlashAttribute("message", Map.of(
                    "type", "danger",
                    "text", "일치하는 아이디나 패스워드가 없습니다."));
            return "redirect:/member/login";
        } else {
            // 로그인 성공
            rttr.addFlashAttribute("message", Map.of(
                    "type", "success",
                    "text", "로그인 되었습니다."));
            session.setAttribute("loggedInMember", member);
            return "redirect:/board/list";
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session, RedirectAttributes rttr) {
        session.invalidate();
        rttr.addFlashAttribute("message", Map.of(
                "type", "success",
                "text", "로그아웃 되었습니다."));

        return "redirect:/member/login";
    }
}