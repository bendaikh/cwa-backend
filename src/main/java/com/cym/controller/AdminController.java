package com.cym.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cym.dao.TeacherDao;
import com.cym.dao.UserDao;
import com.cym.entity.Admin;
import com.cym.entity.Guardian;
import com.cym.entity.Languages;
import com.cym.entity.Role;
import com.cym.entity.Student;
import com.cym.entity.Teacher;
import com.cym.entity.User;
import com.cym.service.GuardianService;
import com.cym.service.JwtService;
import com.cym.service.LanguagesService;
import com.cym.service.StudentService;
import com.cym.service.TeacherService;
import com.cym.service.UserService;
import java.util.HashSet;
@RestController
@CrossOrigin
public class AdminController {
            @PostConstruct
            public void init(){
                List<User> users = userDao.findAll();
                users.forEach(user ->{
                    this.existingUsernames.add(user.getUserName());
                });
            }

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LanguagesService languagesService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GuardianService guardianService;
    private final Set<String> existingUsernames = new HashSet<>();

   

    // add teacher to the admin

    @PostMapping({"/addTeacher"})
    public ResponseEntity<Teacher> addTeacherToUser(@RequestBody Teacher teacher){
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Uuid = ((UserDetails) userPrincipal).getUsername();
        Admin admin  = (Admin) this.userService.findUserByUserName(Uuid);
        Role teachRole = new Role();
        teachRole.setRoleName("Teacher");
        Set<Role> teachRoles = new HashSet<>();
        teachRoles.add(teachRole);
        teacher.setRole(teachRoles);
        Teacher addTeacher = (Teacher) this.userService.registerNewTeacher(teacher);
        List<Long> teacherIds = new ArrayList<Long>();
        teacherIds.add(addTeacher.getId());
        admin.getTeacherIds().addAll(teacherIds);
        userDao.save(admin);
        // user.getTeachers().add(addTeacher.getId());
        return new ResponseEntity<>(
            addTeacher, HttpStatus.OK);
    }

    @PostMapping({"/addLanguages"})
    public Languages addLanguageToAdmin(@RequestBody Languages languages){
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Uuid = ((UserDetails) userPrincipal).getUsername();
        Admin admin  = (Admin) this.userService.findUserByUserName(Uuid);
        Languages savedLanguage = languagesService.saveLanguage(languages);
        admin.getLanguages().add(savedLanguage);
        userDao.save(admin);
        return languages;
    } 


    @PostMapping({"/addStudent"})
    public ResponseEntity<Student> addStudentToUser(@RequestBody Student student)
    {
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Uuid = ((UserDetails) userPrincipal).getUsername();
        Admin admin  = (Admin) this.userService.findUserByUserName(Uuid);
        
        if(this.existingUsernames.contains(student.getUserName()) == true){
            System.out.println("00000000000000000 exist");
            
            return new ResponseEntity<Student>(HttpStatus.CONFLICT);

        }
        
            System.out.println("00000000000000000 no exist");
            Student addStudent = (Student) this.userService.registerNewStudent(student);
            List<Long> studentIds = new ArrayList<Long>();
            studentIds.add(addStudent.getId());
            admin.getStudentIds().addAll(studentIds);
            userDao.save(admin);
            return new ResponseEntity<Student>(addStudent, HttpStatus.CREATED);
        
        
    }


    @PostMapping({"/addGuardian"})
    public Guardian addGuardianToUser(@RequestBody Guardian guardian)
    {
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Uuid = ((UserDetails) userPrincipal).getUsername();
        Admin admin  = (Admin) this.userService.findUserByUserName(Uuid);
        Guardian addGuardian = (Guardian) this.userService.registerNewGuardian(guardian);
        List<Long> gurdianIds = new ArrayList<Long>();
        gurdianIds.add(addGuardian.getId());
        admin.getGuardianIds().addAll(gurdianIds);
        userDao.save(admin);
        // user.getTeachers().add(addTeacher.getId());
        return addGuardian;
    }

    @GetMapping({"/allTeachers"})
    public List<Teacher> allTeachers(){
        return this.teacherService.allTeachers();
    }

    @GetMapping({"/allLanguages"})
    public List<Languages> allLanguages(){
        return languagesService.allLanguage();
    }

    @GetMapping({"/allStudents"})
    public List<Student> allStudents(){
        return studentService.allStudents();
    }

    @GetMapping({"/allGuardians"})
    public List<Guardian> allGuardians(){
        return guardianService.allGuardians();
    }

    

  

//   @GetMapping("/checkUsername")
//   public boolean checkUsername(@RequestParam("value") String value) {
//     return this.existingUsernames.contains(value);
//   }
}
