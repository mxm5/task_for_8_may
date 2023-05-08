package io.mxm.testers.repository;

import io.mxm.testers.domains.Studnet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Studnet, Long> {


}
