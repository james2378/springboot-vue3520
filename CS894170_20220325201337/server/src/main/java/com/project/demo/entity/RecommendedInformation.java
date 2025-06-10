package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *推荐信息：(RecommendedInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RecommendedInformation")
public class RecommendedInformation implements Serializable {

    //RecommendedInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommended_information_id")
    private Integer recommended_information_id;
   // 学生信息
   @Basic
    private Integer student_information;
   // 第一推荐
   @Basic
    private String first_recommendation;
   // 第二推荐
   @Basic
    private String second_recommendation;
   // 第三推荐
   @Basic
    private String third_recommendation;
   // 备注信息
   @Basic
    private String remark_information;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
