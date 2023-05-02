package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.ReportService;

@Controller
@RequestMapping("report")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /** 日報一覧画面を表示（全員分） */
    @GetMapping("/")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("reportlist", service.getReportList());
        // report/list.htmlに画面遷移
        return "report/list";
    }

    /** 日報一覧画面を表示（ログイン本人分） */

    /** 日報の詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    public String getReport(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("report", service.getReport(id));
        // 詳細画面に遷移
        return "report/detail";
    }

    /** 日報の新規作成画面を表示(ルーティングだけのもの） */
    /* @GetMapping("/register")
     * public String getRegister(@ModelAttribute Report report) {
     * return "report/register"; }
     */

    /** 日報の新規登録処理 */
    @GetMapping("/register/")
    public String getRegister(@AuthenticationPrincipal UserDetails user, Model model) {
        Integer id = service.getEmployeeID(user.getUsername());
        model.addAttribute("userid",id);
        return "report/register";
    }

    /** 日報の更新画面を表示 */

    /** 日報の更新処理 */
}
