package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.UserRepository;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(@CookieValue(value = "SESSION", required = false) String session,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) {
        HttpSession httpSession = request.getSession(false);
        if (StringUtils.hasText(session) && Objects.nonNull(httpSession)) {

            Cookie cookie = new Cookie("SESSION", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            httpSession.removeAttribute("id");
            httpSession.invalidate();
        }
        return "redirect:/";
    }
}
