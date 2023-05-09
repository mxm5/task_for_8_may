package io.mxm.testers.repository;

import io.mxm.testers.domains.Identity;
import io.mxm.testers.domains.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IdentityRepository extends JpaRepository<Identity, Long> {
    Identity findByUsername(String username);

    Identity findBySubject(String subject);
}
