package com.techacademy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

/** トップページにログインした人の日報一覧を表示させるための絞り込み　**/
    List<Report> findByEmployee(Employee employee);
}
