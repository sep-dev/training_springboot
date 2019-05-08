package com.jpasample;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("title", "Spring-Boot サンプルぞ。");

        List<Employee> empList=empRepository.findAll();
    	model.addAttribute("emplist", empList);
        
        // src/main/resources/templates/ 下の HTML を出力。
        // この場合は index.html となる。
        return "index";
    }
}
