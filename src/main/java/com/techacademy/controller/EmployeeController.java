package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
/** @RequestMapping("employee")*/
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** 従業員一覧画面を表示 */
    @GetMapping("/")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        // employee/list.htmlに画面遷移
        return "employee/list";
    }

    /** 従業員の詳細を表示 */
    @GetMapping("/detail/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // employee/detail.htmlに画面遷移
        return "employee/detail";
    }

    /** Employee更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String updateEmployee(@PathVariable("id") Integer id, Model model) {
        // ↑当初「ublic String getEmployee」にしていて重複エラーとなったため変更してみた
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // 更新画面に遷移
        return "employee/update";
    }

    /** Employee更新処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(Employee employee) {
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/list";
    }

    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // Employee登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(Employee employee) {
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/list";
    }

    /** Employee削除画面を表示 */
    @GetMapping("/delete/{id}/")
    public String deleteEnployee(@PathVariable("id") Integer id, Model model) {
            // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
            // 削除画面に遷移
        return "employee/delete";
    }

    /** Employee削除処理 */
    @PostMapping("/delete/{id}/")
    public String deletecheckEmployee(Employee employee) {
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/";
    }


}