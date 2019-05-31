package com.pipi.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "fun")
public class Fun  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "fun_id")
	private Integer funId;

	private Integer authority;

	@Column(name = "catalog_id")
	private Integer catalogId;

	@Column(name = "catalog_child_id")
	private Integer catalogChildId;

	private String tag;

	@Column(name = "created_at")
	private java.util.Date createdAt;

	@Column(name = "updated_at")
	private java.util.Date updatedAt;

}
