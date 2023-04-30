/** ★コントローラー★ */

package com.techacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        // 従業員の登録画面に遷移
        return "employee/register";
    }

    /** 従業員の登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated Employee employee, BindingResult res, Model model) {
        // 社員番号が重複する場合に更新処理を行わず登録画面に戻す
        if(res.hasErrors()) {
            // エラーあり
            return getRegister(employee);
        } else {
        // 認証情報をセット
        employee.getAuthentication().setEmployee(employee);
        // パスワードを暗号化
        String inputPassword = employee.getAuthentication().getPassword();
        employee.getAuthentication().setPassword(passwordEncoder.encode(inputPassword));
        // 従業員の登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/";
        }
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
        // フォームから送信されない必須項目の削除フラグをテーブルから取得
        Employee tableEmployee = service.getEmployee(id);
        employee.setDeleteFlag(tableEmployee.getDeleteFlag());
        // 認証情報をセット */
        employee.getAuthentication().setEmployee(employee);
        // パスワードの入力チェック→空だったらIDをキーに認証情報テーブルから現パスワードを取得してセット
        String inputPassword = employee.getAuthentication().getPassword();
        if(inputPassword.equals("")){
        employee.getAuthentication().setPassword(tableEmployee.getAuthentication().getPassword());
        } else {
        // パスワード暗号化
        employee.getAuthentication().setPassword(passwordEncoder.encode(inputPassword));
        }
        // 従業員の更新
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/";
    }

    /** 従業員の削除処理（論理削除） */
    @GetMapping("/delete/{id}/")
    public String deleteEmployee(@PathVariable("id") Integer id, Employee employee) {
        // Employee登録
        Employee employeetable = service.getEmployee(id);
        employeetable.setDeleteFlag(1);
        service.saveEmployee(employeetable);
        // 一覧画面にリダイレクト
        return "redirect:/employee/";
    }
}