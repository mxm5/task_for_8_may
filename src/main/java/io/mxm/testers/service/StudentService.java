package io.mxm.testers.service;

import io.mxm.testers.domains.Lesson;
import io.mxm.testers.domains.Studnet;
import io.mxm.testers.repository.LessonRepository;
import io.mxm.testers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public  class StudentService implements IStudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LessonRepository lessonRepository;

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


}
