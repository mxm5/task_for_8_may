package io.mxm.testers.application;

import io.mxm.testers.MyTestApplication;

import io.mxm.testers.domains.Lesson;
import io.mxm.testers.domains.Studnet;
import io.mxm.testers.domains.Teacher;
import io.mxm.testers.dto.ResponseDto;
import io.mxm.testers.dto.StudentRegisterInfo;
import io.mxm.testers.dto.UsernameAndPasswordDTO;
import io.mxm.testers.repository.LessonRepository;
import io.mxm.testers.repository.StudentRepository;
import io.mxm.testers.repository.TeacherRepository;
import io.mxm.testers.service.IStudentService;
import io.mxm.testers.service.IdentityService;
import io.mxm.testers.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MyTestApplication.class)
@AutoConfigureMockMvc
public class MockTesting {


    @Autowired
    StudentService studentService;

    @Test
   public void encodePass() {
        System.out.println(new BCryptPasswordEncoder().encode("12345"));
    }

    @Test
    public void registerNewStudent() {
        StudentRegisterInfo studentRegisterInfo = new StudentRegisterInfo(
                19, "mohammad", "alavi", "mlx", "12345"
        );
        studentService.registerNewStudent(studentRegisterInfo);
    }

    @Autowired
    IdentityService identityService;
    @Test
    public void loginWithStudentAndGetAJWT() {

        UsernameAndPasswordDTO creds = new UsernameAndPasswordDTO(
                "mlx", "12345"
        );
        ResponseDto login = identityService.login(creds);
        System.out.println(login);
    }

    @Autowired
    StudentRepository studentRepository;


    @Test
    public void createStudent() {
        Studnet studnet = new Studnet(
                17, "mohammad", "hasani", 313L
        );
        studentRepository.save(studnet);
    }

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    public void createTeacher() {
        Teacher teacher = new Teacher("hamid", "karimi", 19, 300L, new HashSet<>());
        teacherRepository.save(teacher);
    }


    @Autowired
    LessonRepository lessonRepository;

    @Test
    public void createLesson() {

        Set<Studnet> students = new HashSet<>();
        students.add(studentRepository.getById(1L));


        Set<Teacher> teachers = new HashSet<>();
        teachers.add(teacherRepository.getById(2L));
        Lesson lesson = new Lesson(
                500L, "math", LocalDateTime.now(), teachers, students
        );

        lessonRepository.save(lesson);
    }


    public void studentSelectedCourse() {
        studentService.selectLesson(1L, 3L);
    }


}
