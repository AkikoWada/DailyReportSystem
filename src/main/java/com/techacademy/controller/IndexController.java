package com.techacademy.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getIndex(Model model) {
        // SecurityContextHolderからAuthenticationオブジェクトを取得
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String loginuser = authentication.getName();

        model.addAttribute("msg", "ログイン名は"+loginuser+"です");

        // index.htmlに画面遷移
        return "index";
}
}
