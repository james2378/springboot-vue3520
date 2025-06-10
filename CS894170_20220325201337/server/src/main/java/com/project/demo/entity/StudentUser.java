package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学生用户：(StudentUser)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudentUser")
public class StudentUser implements Serializable {

    //StudentUser编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_user_id")
    private Integer student_user_id;
   // 姓名
   @Basic
    private String full_name;
   // 性别
   @Basic
    private String gender;
   // 考试分数
   @Basic
    private String test_score;
   // 日语等级
   @Basic
    private String japanese_level;
   // 托业分数
   @Basic
    private String toeic_score;
   // 意向专业
   @Basic
    private String intended_major;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
