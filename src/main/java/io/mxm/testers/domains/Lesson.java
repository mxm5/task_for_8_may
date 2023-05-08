package io.mxm.testers.domains;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Lesson(Long lessonCode, String title, LocalDateTime date, Set<Teacher> teachers, Set<Studnet> studnets) {
        this.lessonCode = lessonCode;
        this.title = title;
        this.date = date;
        this.teachers = teachers;
        this.studnets = studnets;
    }

    @Column(name = "lesson_code")
    private Long lessonCode;


    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToMany(mappedBy = "lessons")
    private Set<Teacher> teachers = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "lessons")
    private Set<Studnet> studnets = new LinkedHashSet<>();

}
