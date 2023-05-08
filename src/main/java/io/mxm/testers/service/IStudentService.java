package io.mxm.testers.service;

import io.mxm.testers.domains.Studnet;

public interface IStudentService {
    Studnet createStudent(Studnet studnet);

    boolean selectLesson(Long studentId, Long lessonId);
}
