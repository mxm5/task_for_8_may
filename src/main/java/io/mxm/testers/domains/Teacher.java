package io.mxm.testers.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Teacher(String firstName, String lastName, Integer age, Long teacherCode, Set<Lesson> lessons) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.teacherCode = teacherCode;
        this.lessons = lessons;
    }

    @Column(name = "first_name",length = 255,nullable = false)
    private String firstName ;
//VALIDATION ها در پیاده سازی رعایت شود. )
// سن دانشجو نباید کمتر از 18 سال باشد-در فیدهای عددی
//چک شود فقط عدد وارد گردد )
// کد دانشجو فیلد عددی است
// (– فیلدهای ضروری شامل : نام و نام خانوادگی
//دانشجو ، کد دانشجویی، انتخاب استاد و درس.
// ( چنانچه هر یک از VALIDATIONها رعایت نشود پیغام
//مناسب در صفحه نمایش داده شود .

    //یک API جهت انتخاب
    // یک درس توسط استاد
    // در یک روز مشخص هفته
    // و در یک بازه زمانی مشخص ) تنها
    //فردی که به عنوان استاد معرفی شده است امکان استفاده
    // از این API را دارد(

    // l1 t1 d1 s1
    // l1 t1 d2 s2
    // l1 t2
    @Column(name = "last_name",length = 255,nullable = false)
    private String lastName ;

    @Min(value = 18,message = "cant be less than 18")
    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "teacher_code")
    private Long teacherCode;

    @ManyToMany
    @JoinTable(name = "teacher_lessons",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "lessons_id"))
    private Set<Lesson> lessons = new LinkedHashSet<>();

    public boolean addLesson(Lesson lesson) {
        return lessons.add(lesson);
    }
}
