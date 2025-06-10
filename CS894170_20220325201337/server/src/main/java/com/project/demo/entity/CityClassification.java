package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *城市分类：(CityClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CityClassification")
public class CityClassification implements Serializable {

    //CityClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_classification_id")
    private Integer city_classification_id;
   // 所属城市
   @Basic
    private String city;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
