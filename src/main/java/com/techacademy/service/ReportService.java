package com.techacademy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Authentication.Role;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.AuthenticationRepository;
import com.techacademy.repository.EmployeeRepository;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository repository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationRepository authRepository;

    public ReportService(ReportRepository repository,EmployeeRepository employeeRepository,AuthenticationRepository authRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.authRepository = authRepository;
    }

    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return repository.findAll();
    }

    /** ★★★＜うまくいかず＞ログインしている従業員の日報だけを返す */
    public List<Report> getMyReportList(UserDetails user) {
        String code = user.getUsername();
        Optional<Authentication> loginemployee = getLoginemployee(code);
        if (loginemployee.isPresent()) {
            Employee employee = loginemployee.get().getEmployee();
            return repository.findByEmployee(employee);
        }
        return null;
    }

    /** 1件検索して返す（詳細表示・更新） */
    public Report getReport(Integer id) {
        return repository.findById(id).get();
    }

    /** 特定の従業員のcodeをキーにIDを返す */
    public Optional<Authentication> getLoginemployee(String code) {
        return authRepository.findById(code);
    }

    /** 日報作成者とログインユーザのIDが同一であることの確認 */
    public boolean checkEqualID(Integer id,@AuthenticationPrincipal UserDetails user){
        //日報作成者IDをチェック
        Report report = getReport(id);
        Integer employeeId = report.getEmployeeId();

        //ログインユーザIDをチェック
        String code = user.getUsername();
        Optional<Authentication> loginemployee = getLoginemployee(code);
        if (loginemployee.isPresent()) {
            Integer loginemployeeId = loginemployee.get().getEmployee().getId();
            //日報作成者ID＝ログインユーザIDであればTrueを返す
            return employeeId==loginemployeeId;
        }
        return false;
      }

    /** 日報の登録を行なう */
    @Transactional
    public Report saveReport(Report report) {
        return repository.save(report);
    }
}
