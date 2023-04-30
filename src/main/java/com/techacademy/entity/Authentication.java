package com.techacademy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** 権限用の列挙型 */
    public static enum Role {
        一般, 管理者
    }

    /** 社員番号 ログイン時に使う */
    @Id
    @Length(max=20)
    @NotEmpty
    private String code;

    /** パスワード */
    @Length(max=255)
    @NotEmpty
    private String password;

    /** 権限　3桁　列挙型（文字列） */
    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private Role role;

    /** 従業員テーブルのID */
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;
}
