package com.cym.service;

import com.cym.dao.RoleDao;
import com.cym.dao.UserDao;
import com.cym.entity.Admin;
import com.cym.entity.Guardian;
import com.cym.entity.Role;
import com.cym.entity.Student;
import com.cym.entity.Teacher;
import com.cym.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        // Role adminRole = new Role();
        // adminRole.setId(20L);
        // adminRole.setRoleName("Admin");
        // adminRole.setRoleDescription("Admin role");
        // roleDao.save(adminRole);

        // Role studentRole = new Role();
        // studentRole.setId(2L);
        // studentRole.setRoleName("Student");
        // studentRole.setRoleDescription("Student role");
        // roleDao.save(studentRole);

        // Role userRole = new Role();
        // userRole.setId(2L);
        // userRole.setRoleName("User");
        // userRole.setRoleDescription("Default role for newly created record");
        // roleDao.save(userRole);

        
        
        // Role teachRole = new Role();
        // teachRole.setId(9L);
        // teachRole.setRoleName("Teacher");
        // teachRole.setRoleDescription("Teach role");
        // roleDao.save(teachRole);

        // Role guardRole = new Role();
        // guardRole.setId(20L);
        // guardRole.setRoleName("Guardian");
        // guardRole.setRoleDescription("Guardian role");
        // roleDao.save(guardRole);

        // Teacher teacher = new Teacher();
        // teacher.setId(8L);
        // teacher.setUserName("teach1235");
        // teacher.setUserPassword(getEncodedPassword("teach5"));
        // teacher.setAddress("ait saleh");
        // teacher.setClassName("hifz");
        // Set<Role> teachRoles = new HashSet<>();
        // teachRoles.add(teachRole);
        // teacher.setRole(teachRoles);
        // userDao.save(teacher);

        // Admin adminUser = new Admin();
        // adminUser.setId(80L);
        // adminUser.setUserName("admin1234");
        // adminUser.setUserPassword(getEncodedPassword("admin"));
        // adminUser.setUserFirstName("admin");
        // adminUser.setUserLastName("admin");
        // Set<Role> adminRoles = new HashSet<>();
        // adminRoles.add(adminRole);
        // adminUser.setRole(adminRoles);
        // userDao.save(adminUser);
        // adminUser.getTeachers().add(savedTeacher.getFullname());
        
        
//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewAdmin(User user) {
        Role role = roleDao.findByRoleName("Admin");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public User registerNewTeacher(Teacher teacher) {
        Role role = roleDao.findByRoleName("Teacher");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        teacher.setRole(userRoles);
        teacher.setUserPassword(getEncodedPassword(teacher.getUserPassword()));
        return userDao.save(teacher);
    }

    public User registerNewStudent(Student student) {
        Role role = roleDao.findByRoleName("Student");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        student.setRole(userRoles);
        student.setUserPassword(getEncodedPassword(student.getUserPassword()));
        return userDao.save(student);
    }

    public User registerNewGuardian(Guardian guardian) {
        Role role = roleDao.findByRoleName("Guardian");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        guardian.setRole(userRoles);
        guardian.setUserPassword(getEncodedPassword(guardian.getUserPassword()));
        return userDao.save(guardian);
    }
    

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User findUserByUserName(String userName){
        return this.userDao.findByUserName(userName);
    }
}
