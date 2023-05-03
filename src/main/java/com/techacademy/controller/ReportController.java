package com.techacademy.controller;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Report;
import com.techacademy.repository.AuthenticationRepository;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

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

    /** ★★★＜うまくいかず＞日報一覧画面を表示（ログインユーザ本人分） */
    @GetMapping("/")
    public String getMyList(@AuthenticationPrincipal UserDetails user,Model model) {
        // ログインユーザ本人分の結果のみをModelに登録
        model.addAttribute("reportlist", service.getMyReportList(user));
        // report/index.htmlに画面遷移
        return "report/index";
    }

    /** 日報の詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    public String getReport(@PathVariable("id") Integer id,@AuthenticationPrincipal UserDetails user,Model model) {
        model.addAttribute("report",service.getReport(id));
        // 同一ユーザかどうかをチェック
        if(service.checkEqualID(id,user)==true) {
            // 詳細画面に遷移
            return "report/detail";
        } else {
            // 編集不許可フラグを付加して詳細画面に遷移
            model.addAttribute("updateDisallowed","1");
            return "report/detail";
        }
    }

    /** 日報の新規作成画面を表示 */
    @GetMapping("/register")
    public String getRegister(@AuthenticationPrincipal UserDetails user, Model model) {
        String code = user.getUsername();
        Optional<Authentication> loginemployee = service.getLoginemployee(code);
        if (loginemployee.isPresent()) {
            Integer id = loginemployee.get().getEmployee().getId();
            model.addAttribute("id",id);
            return "report/register";
        }
        return "report/register";
    }

    /** 日報の新規登録処理 */
    @PostMapping("/register")
    public String postRegister(Report report) {
        // 日報登録
        service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/";
    }

    /** 日報の更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String updateReport(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("report", service.getReport(id));
        // 同一ユーザかどうかをチェック
        if(service.checkEqualID(id,user)==true) {
            //trueなら更新画面に遷移
            return "report/update";
        } else {
            // （必要はないけれど念のため）メッセージを付加して詳細ページを開く
            model.addAttribute("updaterole","日報は作成者本人のみ編集可能です");
            return "report/detail";
        }
    }

    /** 日報の更新処理 */
    @PostMapping("/update/{id}/")
    public String postReport(@PathVariable("id") Integer id, Report report) {
        // 従業員の更新
        service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/";
    }
}
