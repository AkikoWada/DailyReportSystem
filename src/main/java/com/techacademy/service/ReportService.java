package com.techacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.AuthenticationRepository;
import com.techacademy.repository.EmployeeRepository;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository repository;
    private final AuthenticationRepository authRepository;

    public ReportService(ReportRepository repository,AuthenticationRepository authRepository) {
        this.repository = repository;
        this.authRepository = authRepository;
    }

    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return repository.findAll();
    }

    /** 特定の従業員の日報だけを検索して返す */
    public List<Report> getmyReportList(Employee employee) {
        // リポジトリのfindAllメソッドを呼び出す
        return repository.findByEmployee(employee);
    }

    /** 1件検索して返す（詳細表示・更新） */
    public Report getReport(Integer id) {
        return repository.findById(id).get();
    }

    /** 特定の従業員のcodeからIDを返す */
    public Integer getEmployeeID(String code) {
        return authRepository.getEmployeeId();
    }

}
