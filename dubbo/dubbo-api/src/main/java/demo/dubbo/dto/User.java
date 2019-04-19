package demo.dubbo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : gb 2019/4/18
 */
@Data
@Builder
public class User implements Serializable {
    private int id;
    private String name;
    private int sex;
    private int age;
    private String address;
}