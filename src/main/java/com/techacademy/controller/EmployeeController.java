/** ★コントローラー★ */

package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
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

    /** 従業員の詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // 詳細画面に遷移
        return "employee/detail";
    }

    /** 従業員の登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // Employee登録画面に遷移
        return "employee/register";
    }

    /** 従業員の登録処理 */
    @PostMapping("/register")
    public String postRegister(Employee employee) {
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト（→一時的に/）
        return "redirect:/";
    }

    /** 従業員の更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String updateEmployee(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // 更新画面に遷移
        return "employee/update";
    }

    /** 従業員の更新処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(@PathVariable("id") Integer id, Employee employee) {
        /** フォームから送信される項目はID、コード、名前、パスワード、権限 */
        /** フォームから送信されない必須項目は削除フラグ */
        Employee tableEmployee = service.getEmployee(id);
        employee.setDeleteFlag(tableEmployee.getDeleteFlag());
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト（→一時的に/）
        return "redirect:/";
    }

    /** 従業員の削除処理（論理削除） */
    @GetMapping("/delete/{id}/")
    public String deleteEmployee(@PathVariable("id") Integer id, Employee employee) {
        // Employee登録
        Employee employeetable = service.getEmployee(id);
        employeetable.setDeleteFlag(1);
        service.saveEmployee(employeetable);
        // 一覧画面にリダイレクト
        return "redirect:/";
    }
}