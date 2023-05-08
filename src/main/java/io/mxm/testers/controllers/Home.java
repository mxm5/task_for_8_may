package io.mxm.testers.controllers;

import io.mxm.testers.dto.ResponseDto;
import io.mxm.testers.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Home {

    @GetMapping("/")
    public String getHome() {
        return "<h1 style=\"font-size: 90px;\">  welcome home </h1>";
    }

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacher/offercourse")
    public ResponseDto teacherOfferCourse(@RequestParam("courseId") Long courseId, @RequestParam("teacherId") Long teacherId ) {
        teacherService.teacherOfferedCourse(teacherId,courseId);
        return new ResponseDto();


    }
}
