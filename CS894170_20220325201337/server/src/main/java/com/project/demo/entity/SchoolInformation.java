package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学校信息：(SchoolInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SchoolInformation")
public class SchoolInformation implements Serializable {

    //SchoolInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_information_id")
    private Integer school_information_id;
   // 学校名称
   @Basic
    private String school_name;
   // 学校排名
   @Basic
    private String school_ranking;
   // 所属城市
   @Basic
    private String city;
   // 建校时间
   @Basic
    private String founding_year_;
   // 学校图片
   @Basic
    private String school_pictures;
   // 招生分数线
   @Basic
    private String enrollment_score_line;
   // 招生时间
   @Basic
    private String enrollment_time;
   // 咨询电话
   @Basic
    private String consultation_telephone;
   // 学校地址
   @Basic
    private String school_address;
   // 学校介绍
   @Basic
    private String school_introduction;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
