package io.mxm.testers.repository;

import io.mxm.testers.domains.Studnet;
import io.mxm.testers.domains.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}
