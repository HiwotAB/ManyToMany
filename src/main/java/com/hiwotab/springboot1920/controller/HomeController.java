package com.hiwotab.springboot1920.controller;

import com.hiwotab.springboot1920.model.Courses;
import com.hiwotab.springboot1920.model.Role;
import com.hiwotab.springboot1920.model.Student;
import com.hiwotab.springboot1920.model.User;
import com.hiwotab.springboot1920.repositories.CourseRepo;
import com.hiwotab.springboot1920.repositories.RoleRepo;
import com.hiwotab.springboot1920.repositories.StudentRepo;
import com.hiwotab.springboot1920.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
@Controller
public class HomeController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    StudentRepo studentRepo;
    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
            return "login";
        }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
    @RequestMapping("/secure")
    public String secure() {
        return "secure";
    }
    @GetMapping("/signUpForm")
    public String addUser(Model model) {
        model.addAttribute("newUser", new User());
        return "signUpForm";
    }
    @PostMapping("/signUpForm")
    public String addUserConfirm(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult , Model model) {
        if (bindingResult.hasErrors()) {
            return "signUpForm";
        }

//        user.setEnabled(true);
//        Role role=roleRepo.findOne(new Long(1));
//        Set<Role> roleSet = new HashSet<Role>();
//        roleSet.add(role);
//        user.setRoles(roleSet);
//        userRepo.save(user);
//        return "signUpConfirm";

        Set<Role> roleSet = new HashSet<Role>();
        Role byRole = roleRepo.findByRole("USER");
        roleSet.add(byRole);
        user.setRoles(roleSet);
        roleRepo.save(byRole);
        userRepo.save(user);
        return "signUpConfirm";
    }
    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
    @GetMapping("addStudentToCourse/{id}")
    public String addStudentToCourses(@PathVariable("id") long courseId, Model model)
    {
        model.addAttribute("crs", CourseRepo.findOne(new Long(courseId)));
        model.addAttribute("perlist",studentRepo.findAll());
        return "addStudentToCourse";
    }

    @PostMapping("addStudentToCourse/{crsid}")
    public String postPeopletoCourse(@PathVariable("crsid") long courseID,
                                     @RequestParam("people") String personId,
                                     @ModelAttribute("aPerson") Student p,
                                     Model model)
    {


        Courses cr=courseRepo.findOne(new Long(courseID));
        cr.addStudent(studentRepo.findOne(new Long(personId)));
        courseRepo.save(cr);
        model.addAttribute("personlist",studentRepo.findAll());
        model.addAttribute("courselist",courseRepo.findAll());

        return "redirect:/courselist";
    }

    @GetMapping("/courselist")
    public String listCourse(Model model)
    {
        model.addAttribute("course", courseRepo.findAll());
        return"courselist";
    }

    @GetMapping("/courses for student/{id}")
    public String CousesForStudent(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("coursreg", courseRepo.findCoursesById(id));
        return"studentscourse";
    }

}
