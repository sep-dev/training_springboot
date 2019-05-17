package com.jpasample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 社員データエンティティクラス
 *
 */
@Entity
@Table(name="employee")
public class Employee {

	@Id
    @Column(name="empno")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@NotEmpty(message = "社員名を入力してください。")
	@Size(max = 10, message = "社員名は10文字以内で入力してください。")
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
