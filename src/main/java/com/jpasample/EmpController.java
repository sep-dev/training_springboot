package com.jpasample;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * コントローラクラス
 */
@Controller
public class EmpController {
	@Autowired
	EmployeeService empService;
    
	/**
	 * ページング単位の社員一覧出力メソッド。
	 * @param model
	 * @param pageable
	 * @param search
	 * @return
	 */
	@GetMapping(value = "/emp")
    public String index(Model model, Pageable pageable, String search) {

		if (Objects.isNull(search)) {
			search = "";
		}

		String query = !search.equals("") ? "?search=" + search : "";
		
        // ページング単位で社員データ一覧を取得。
        Page<Employee> empPage = empService.getSearch(search, pageable);
        model.addAttribute("page", empPage);
        model.addAttribute("url", "/emp" + query);

    	// 新規登録用Employee
    	Employee new_employee = new Employee();
    	model.addAttribute("new_emp", new_employee);
    	
        // src/main/resources/templates/ 下の HTML を出力。
        // この場合は index.html となる。
        return "index";
    }

	/**
	 * 検索社員一覧出力メソッド。
	 * POSTされた検索文字列入力内容 search を元に、社員一覧を部分一致検索で取得し返却する。
	 * @param model
	 * @param pageable
	 * @param search
	 * @return
	 */
	@PostMapping(value = "/emp")
    public String search(Model model, Pageable pageable, String search) {
		
		if (Objects.isNull(search)) {
			search = "";
		}

		Page<Employee> empPage = empService.getSearch(search, pageable);
        model.addAttribute("page", empPage);
        model.addAttribute("url", "/emp?search=" + search);

    	// 新規登録用Employee
    	Employee new_employee = new Employee();
    	model.addAttribute("new_emp", new_employee);

    	return "index";
    }

    /**
     * 社員詳細出力メソッド。
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value="/emp/{id}")
    public String detail(@PathVariable Long id, Model model) {
    	
    	Employee employee = empService.getOne(id);
    	model.addAttribute("emp", employee);

        return "detail";
    }

    /**
     * 社員データ新規登録メソッド。
     * @param new_employee
     * @param result
     * @param model
     * @param pageable
     * @return
     */
    @PostMapping(value="/emp/create")
    public String create(@ModelAttribute @Validated Employee new_employee, BindingResult result, Model model, Pageable pageable) {

    	// 入力エラー時、エラーメッセージ出力
    	if (result.hasErrors()) {
    		model.addAttribute("errors", result.getAllErrors());

        	return index(model, pageable, "");
    	}
    	
    	empService.save(new_employee);

    	// 登録後社員一覧にリダイレクト
        return "redirect:/emp";
    }

    /**
     * 社員データ編集確認メソッド。
     * @param id
     * @param employee
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value="/emp/{id}/confirm")
    public String confirm(@PathVariable Long id, @ModelAttribute @Validated Employee employee, BindingResult result, Model model) {

    	// 入力エラー時、エラーメッセージ出力
    	if (result.hasErrors()) {
    		model.addAttribute("errors", result.getAllErrors());
        	model.addAttribute("emp", employee);

            return "detail";
    	}
    	
    	model.addAttribute("emp", employee);

        return "detail_confirm";
    }

    /**
     * 社員データ更新メソッド。
     * @param id
     * @param employee
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value="/emp/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute Employee employee, BindingResult result, Model model) {

    	employee.setId(id);
    	empService.save(employee);

    	model.addAttribute("emp_name", employee.getName());

        return "detail_complete";
    }

    /**
     * 社員データ削除メソッド。
     * @param id
     * @param model
     * @return
     */
    @DeleteMapping(value="/emp/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
    	
    	empService.delete(id);

        return "redirect:/emp";
    }
    
}