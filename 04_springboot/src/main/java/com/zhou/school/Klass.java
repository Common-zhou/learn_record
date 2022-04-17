package com.zhou.school;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoubing
 * @date 2022-04-14 00:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Klass {

    private List<Student> students;

}
