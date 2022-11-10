package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("groups")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;

    @Autowired
    public GroupController(GroupService groupService, CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    @ModelAttribute("courseList")
    public List<Course> findAllCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping
    public String getAllGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "group/groups";

    }

    @GetMapping("/addGroup")
    public String addGroup(Model model) {
        model.addAttribute("group", new Group());
        return "group/addGroup";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.saveGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/updateGroup/{id}")
    public String updateGroup(@PathVariable("id") Long id, Model model) {
    Group group = groupService.getGroupById(id);
    model.addAttribute("group", group);
    return "group/updateGroup";
    }

    @PatchMapping("/{id}")
    public String saveUpdateGroup(@PathVariable("id") Long id, @ModelAttribute("group") Group group) {
        groupService.updateGroup(group, id);
        return "redirect:/groups";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") Long id) {
        System.out.println("before delete " + groupService.getGroupById(id).toString());
        Group group = groupService.getGroupById(id);
        groupService.deleteGroup(group);
        System.out.println("after delete " + groupService.getGroupById(id).toString());
        return "redirect:/groups";
    }
}

