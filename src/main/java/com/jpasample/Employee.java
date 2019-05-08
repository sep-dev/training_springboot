package com.jpasample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// データベース employee テーブルのデータ格納するエンティティクラス
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @Column(name="empno")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String empname;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmpname() {
        return empname;
    }
    public void setEmpname(String empname) {
        this.empname = empname;
    }
}
