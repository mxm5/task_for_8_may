package io.mxm.testers.service;

import io.mxm.testers.domains.Lesson;
import io.mxm.testers.domains.Studnet;
import io.mxm.testers.domains.Teacher;
import io.mxm.testers.repository.LessonRepository;
import io.mxm.testers.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService implements ITeacherService{
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public Boolean teacherOfferedCourse(Long teacherId, Long courseId) {
        Optional<Teacher> byId =
                teacherRepository.findById(teacherId);
        if (!byId.isPresent()) {
            throw new IllegalStateException("Teacher not found");
        }

        Optional<Lesson> byId1 = lessonRepository.findById(courseId);
        if (!byId1.isPresent()) {
            throw new IllegalStateException("lesson not found");
        }
        Teacher foundTeacher = byId.get();
        Lesson lesson = byId1.get();
        return foundTeacher.addLesson(lesson);

    }
}
