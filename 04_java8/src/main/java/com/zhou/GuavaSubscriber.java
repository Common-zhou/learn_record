package com.zhou;

import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoubing
 * @date 2022-04-13 22:52
 */
public class GuavaSubscriber {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Student {
        private String name;
        private String city;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudentEvent {
        private Student student;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TeacherEvent {
        private String teacherName;
    }

    @Subscribe
    public void studentSubscribe(StudentEvent studentEvent) {
        System.out.println("receive one student subscribe." + studentEvent);
    }

    @Subscribe
    public void teacherSubscribe(TeacherEvent teachEvent) {
        System.out.println("receive one teacher subscribe." + teachEvent);
    }
}
