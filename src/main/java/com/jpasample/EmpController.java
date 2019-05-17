package com.jpasample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String index(Model model, Pageable pageable) {
		
        // ページング単位で社員データ一覧を取得。
        Page<Employee> empPage = empService.getAll(pageable);
        model.addAttribute("page", empPage);
        model.addAttribute("emplist", empPage.getContent());
        model.addAttribute("url", "/emp");

    	// 新規登録用Employee
    	Employee new_employee = new Employee();
    	model.addAttribute("new_emp", new_employee);
    	
        // src/main/resources/templates/ 下の HTML を出力。
        // この場合は index.html となる。
        return "index";
    }

    /**
     * 社員詳細出力メソッド。
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
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
    @RequestMapping(value="/emp/create", method=RequestMethod.POST)
    public String create(@ModelAttribute @Validated Employee new_employee, BindingResult result, Model model, Pageable pageable) {

    	// 入力エラー時、エラーメッセージ出力
    	if (result.hasErrors()) {
    		model.addAttribute("errors", result.getAllErrors());

        	return index(model, pageable);
    	}
    	
    	empService.save(new_employee);

    	// 登録後社員一覧にリダイレクト
        return "redirect:/emp";
    }

    /**
     * 社員データ更新メソッド。
     * @param id
     * @param employee
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value="/emp/{id}/update", method=RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute @Validated Employee employee, BindingResult result, Model model) {

    	// 入力エラー時、エラーメッセージ出力
    	if (result.hasErrors()) {
    		model.addAttribute("errors", result.getAllErrors());
        	model.addAttribute("emp", employee);

            return "detail";
    	}
    	
    	employee.setId(id);
    	empService.save(employee);

    	// 更新した社員名を完了ページに出力。
    	model.addAttribute("empname", employee.getEmpname());

        return "detail_complete";
    }

    /**
     * 社員データ削除メソッド。
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/emp/{id}/delete", method=RequestMethod.DELETE)
    public String delete(@PathVariable Long id, Model model) {
    	
    	empService.delete(id);

        return "redirect:/emp";
    }
    
}