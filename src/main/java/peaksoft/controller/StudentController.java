package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @ModelAttribute("groupList")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }


    @GetMapping
    public String getAllStudent(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        // model.addAttribute("group", groupService.getGroupById(id));
        return "student/students";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        /*   model.addAttribute("group", groupService.getGroupById(id));*/
        return "student/addStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student/updateStudent";
    }

    @PatchMapping("/saveUpdateStudent/{id}")
    public String saveUpdateStudent(@ModelAttribute("student") Long id, Student student) {
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(studentService.getStudentById(id));
        return "redirect:/students";
    }
}