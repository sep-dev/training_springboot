package com.jpasample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 社員データエンティティクラス
 *
 */
@Entity
@Table(name="employee")
public class Employee {

	/**
	 * ID メンバ。
	 */
	@Id
    @Column(name="empno")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	/**
	 * 社員名メンバ。
	 * 必須かつ10文字以内であること。
	 */
	@Column(name="empname")
	@NotEmpty(message = "社員名を入力してください。")
	@Size(max = 10, message = "社員名は10文字以内で入力してください。")
    private String name;
    
	/**
	 * 電話番号メンバ。
	 * 空白、もしくは電話番号形式であること。
	 */
	@Pattern(regexp = "^(|0\\d{1,4}-\\d{1,4}-\\d{4})$", message = "電話番号の形式が正しくありません。半角数字（ハイフンあり）で入力してください。")
	@Size(max = 20, message = "電話番号は20文字以内で入力してください。")
	private String tel;

	/**
	 * 部署IDメンバ。
	 */
    private Long section_id;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getTel() {
    	return tel;
    }
    
    public void setTel(String tel) {
    	this.tel = tel;
    }
    
    public Long getSection_id() {
    	return section_id;
    }
    
    public void setSection_id(Long section_id) {
    	this.section_id = section_id;
    }
}
