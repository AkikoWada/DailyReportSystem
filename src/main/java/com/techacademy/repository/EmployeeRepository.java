package com.techacademy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techacademy.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /** ★認証テーブルに同じcodeがないかどうかを検証する（→うまくいかない） */
    public static boolean isExistCode(String code) {
        String sql = "SELECT COUNT(*) FROM authentication WHERE code = code";
        if (sql.equals("0")) {
            return false;
        }
        return true;
    }
}