package com.techacademy.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "report")
@EntityListeners(AuditingEntityListener.class)
public class Report {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 日報の日付 */
    @Column(nullable = false,name="report_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    /** タイトル　255桁 empty不許可 */
    @Column(length = 255, nullable = false)
    @NotEmpty
    @Length(max=255)
    private String title;

    /** 内容 empty不許可　Longtext */
    @Column(nullable = false)
    @NotEmpty
    @Type(type="text")
    private String content;

    /** 登録日時 */
    @CreatedDate
    @Column(updatable = false,name="created_at")
    private Timestamp createdAt;

    /** 更新日時 */
    @LastModifiedDate
    @Column(name="updated_at")
    private Timestamp updatedAt;

    /** 従業員テーブルのID INT 外部キー（多対1） */
    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;

    public Integer getEmployeeId() {
        return employee.getId();
    }
}