package io.mxm.testers.domains;

import io.mxm.testers.repository.LessonRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Studnet {
    public Studnet(Integer age, String firstName, String lastName, Long studentCode) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCode = studentCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//VALIDATION ها در پیاده سازی رعایت شود. )
// سن دانشجو نباید کمتر از 18 سال باشد-در فیدهای عددی
//چک شود فقط عدد وارد گردد )

    @Min(value = 18, message = "cant be less than 18")
    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @Column(name = "student_code")
    private Long studentCode;

    @ManyToMany
    @JoinTable(name = "studnet_lessons",
            joinColumns = @JoinColumn(name = "studnet_id"),
            inverseJoinColumns = @JoinColumn(name = "lessons_id"))
    private Set<Lesson> lessons = new LinkedHashSet<>();

    public boolean addLesson(Lesson lesson) {
        return lessons.add(lesson);
    }

}
