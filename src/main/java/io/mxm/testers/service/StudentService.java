package io.mxm.testers.service;

import io.mxm.testers.domains.Identity;
import io.mxm.testers.domains.Lesson;
import io.mxm.testers.domains.Studnet;
import io.mxm.testers.dto.ResponseDto;
import io.mxm.testers.dto.StudentRegisterInfo;
import io.mxm.testers.repository.IdentityRepository;
import io.mxm.testers.repository.LessonRepository;
import io.mxm.testers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
public class StudentService implements IStudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    private IdentityRepository identityRepository;

    @Override
    public Studnet createStudent(Studnet studnet) {
        return studentRepository.save(studnet);
    }

    @Override
    @Transactional
    public boolean selectLesson(Long studnetId, Long lessonId) {
        Optional<Studnet> byId =
                studentRepository.findById(studnetId);
        if (!byId.isPresent()) {
            throw new IllegalStateException("student not found");
        }

        Optional<Lesson> byId1 = lessonRepository.findById(lessonId);
        if (!byId1.isPresent()) {
            throw new IllegalStateException("lesson not found");
        }
        Studnet foundStudent = byId.get();
        Lesson lesson = byId1.get();
        return foundStudent.addLesson(lesson);

    }


    public ResponseDto registerNewStudent(StudentRegisterInfo info) {

        Studnet studnet = new Studnet(info.getAge(), info.getFirstname(), info.getLastname(), generateStudentCode());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = info.getPassword();
        Identity identity = new Identity(info.getUsername(), bCryptPasswordEncoder.encode(password), studnet);
        Studnet student1 = studentRepository.save(studnet);
        Identity identity1 = identityRepository.save(identity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(student1);
        return responseDto;

    }

    private Long generateStudentCode() {
        return new Random().nextLong();
    }
}
