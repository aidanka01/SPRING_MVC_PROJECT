package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.service.CompanyService;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public String getAllCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "company/companies";
    }

    @GetMapping("/addCompany")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company/addCompany";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/updateCompany/{id}")
    public String updateCompany(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "company/updateCompany";
    }

    @PatchMapping("/companyUpdate/{id}")
    public String saveUpdateCompany(@PathVariable("id") Long id, @ModelAttribute("company") Company company) {
        companyService.updateCompany(company, id);
        return "redirect:/companies";
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(companyService.getCompanyById(id));
        return "redirect:/companies";
    }


}
