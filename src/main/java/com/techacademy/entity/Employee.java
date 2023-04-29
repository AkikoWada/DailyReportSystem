package com.techacademy.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
@Where(clause = "delete_flag = 0")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 名前。20桁。null不許可 */
    @Column(length = 20, nullable = false)
    @NotEmpty
    @Length(max=20)
    private String name;

    /** 削除フラグ */
    @Column(name="delete_flag")
    private Integer deleteFlag;

    /** 登録日時 */
    @CreatedDate
    @Column(updatable = false,name="created_at")
    private Timestamp createdAt;

    /** 更新日時 */
    @LastModifiedDate
    @Column(name="updated_at")
    private Timestamp updatedAt;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Authentication authentication;

    /** レコードが削除される前に行なう処理 */
    @PreRemove
    @Transactional
    private void preRemove() {
        // 認証エンティティからemployeeを切り離す
        if (authentication!=null) {
            authentication.setEmployee(null);
        }
    }
}