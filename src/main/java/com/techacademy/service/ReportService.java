package com.techacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findAll();
    }

    /** 1件検索して返す（詳細表示・更新） */
    public Report getReport(Integer id) {
        return reportRepository.findById(id).get();
    }

}
