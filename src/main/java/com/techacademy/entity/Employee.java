package com.techacademy.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
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
    @NotEmpty
    private Integer deleteFlag;

    /** 登録日時 */
    @Column(updatable = false,name="created_at")
    @NotEmpty
    private Date createdAt;

    /** 更新日時 */
    @Column(name="updated_at")
    @NotEmpty
    private Date updatedAt;

    @OneToOne(mappedBy="employee")
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