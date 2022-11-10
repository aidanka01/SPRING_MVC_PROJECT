package peaksoft.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
    private final CourseService courseService;
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(CourseService courseService, InstructorService instructorService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    @ModelAttribute("coursesList")
    public List<Course> getAllCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping
    private String getAllInstructors(Model model) {
        List<Instructor> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);
        return "instructor/instructors";
    }

    @GetMapping("/addInstructor")
    public String addInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor/addInstructor";
    }

    @PostMapping("/saveInstructor")
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor) {
        instructorService.saveInstructor(instructor, instructor.getCourseId());
        return "redirect:/instructors";
    }

    @GetMapping("/updateInstructor/{id}")
    public String updateInstructors(@PathVariable Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        return "instructor/updateInstructor";
    }

    @PatchMapping("/saveUpdateInstructor")
    public String saveUpdateInstructor(@ModelAttribute("instructor") Instructor instructor, Long courseId) {
        instructorService.updateInstructor(instructor, instructor.getCourseId());
        return "redirect:/instructors";
    }

    @DeleteMapping("{id}")
    public String deleteInstructor(@PathVariable("id") Long id) {
        instructorService.deleteInstructor(instructorService.getInstructorById(id));
        return "redirect:/instructors";
    }
}
