package com.pipi.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "fun_images")
public class FunImages implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fun_id")
    private Integer funId;

    @Column(name = "image_url")
    private String imageUrl;

    private Integer authority;

    private Integer blur;

    private String description;

    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    public FunImages(String imageUrl, Date createdAt, Date updatedAt) {
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public FunImages(Integer id ,Integer funId, Integer blur, String description, Date updatedAt) {
        this.id = id;
        this.funId = funId;
        this.blur = blur;
        this.description = description;
        this.updatedAt = updatedAt;
    }

}