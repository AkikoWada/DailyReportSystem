package com.techacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Employee;
import com.techacademy.repository.AuthenticationRepository;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final AuthenticationRepository authRepository;

    public EmployeeService(EmployeeRepository repository, AuthenticationRepository authRepository) {
        this.repository = repository;
        this.authRepository = authRepository;
    }

    /** 全件を検索して返す */
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return repository.findAll();
    }

    /** 1件検索して返す（詳細表示・更新） */
    public Employee getEmployee(Integer id) {
        return repository.findById(id).get();
    }

    /** 従業員の登録を行なう */
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    /** 認証テーブルに同じcodeがないかどうかを検証する */
    public boolean checkCode(String code) {
        return authRepository.existsByCode(code);
    }
}
