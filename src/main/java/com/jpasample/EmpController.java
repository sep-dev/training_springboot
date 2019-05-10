package com.jpasample;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// インタフェース EmployeeRepository でデータを取得し、HTMLテンプレート（ビュー）に出力するコントローラクラス
@Controller
public class EmpController {
	@Autowired
	EmployeeRepository empRepository;
    
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String index(Model model) {
		
		// html テンプレート上 ${変数名}にセットされる。
        model.addAttribute("title", "社員リスト");

        // 既に存在するEmployeeデータ取得
        List<Employee> empList=empRepository.findAll();
    	model.addAttribute("emplist", empList);

    	// 新規登録用Employee
    	Employee new_employee = new Employee();
    	model.addAttribute("new_emp", new_employee);
    	
        // src/main/resources/templates/ 下の HTML を出力。
        // この場合は index.html となる。
        return "index";
    }

    // 社員詳細
    @RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
    public String detail(@PathVariable Long id, Model model) {
    	
    	Employee employee = empRepository.getOne(id);
    	model.addAttribute("emp", employee);

        return "detail";
    }

	// 新規登録処理。画面入力 empname をセットした Employee オブジェクトを EmployeeRepository に渡し新規追加
    @RequestMapping(value="/emp/create", method=RequestMethod.POST)
    public String create(@ModelAttribute Employee new_employee, Model model) {

    	empRepository.save(new_employee);

    	// 登録後社員一覧にリダイレクト
        return "redirect:/emp";
    }

    // URLパス上の id に一致する Employee データを更新
    @RequestMapping(value="/emp/{id}/update", method=RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute Employee employee, Model model) {

    	employee.setId(id);
    	empRepository.save(employee);

        return "redirect:/emp";
    }

    // URLパス上の id に一致する Employee データを削除
    @RequestMapping(value="/emp/{id}/delete", method=RequestMethod.DELETE)
    public String delete(@PathVariable Long id, Model model) {
    	
    	empRepository.deleteById(id);

        return "redirect:/emp";
    }
    
}