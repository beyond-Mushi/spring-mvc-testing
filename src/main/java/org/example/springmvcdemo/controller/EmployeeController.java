package org.example.springmvcdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springmvcdemo.ds.Employee;
import org.example.springmvcdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/")
    public String listAllEmployees(Model model) {
        model.addAttribute("employees", service.findAllEmployees());
        model.addAttribute("success", model.containsAttribute("success"));
        model.addAttribute("update", model.containsAttribute("update"));

        return "index";
    }

    @PostMapping("save-employee")
    public String createEmployee(@Valid Employee employee,
                                 BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "employeeForm";
        }
        service.createEmployee(employee);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/";
    }

    @GetMapping("/employee-form")
    public String employeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeForm";
    }

    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam int id) {
        service.deleteEmployeeById(id);
        return "redirect:/";
    }

    int id;
    @GetMapping("/update-employee")
    public String updateEmployee(Model model, @RequestParam int id) {
        model.addAttribute("employee", service.findEmployeeById(id));
        this.id = id;
        return "updateForm";
    }

    @PostMapping("/process-employee")
    public String processEmployee(@Valid Employee employee,
                                  BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "updateForm";
        }
        service.updateEmployee(employee, this.id);
        redirectAttributes.addFlashAttribute("update", true);
        return "redirect:/";
    }
}
