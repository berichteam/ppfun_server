package com.pipi.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "fun")
public class Fun  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	private Integer authority;

	private String password;

	private BigDecimal fee;

	@Column(name = "catalog_id")
	private Integer catalogId;

	@Column(name = "catalog_child_id")
	private Integer catalogChildId;

	private String tag;

	@Column(name = "created_at")
	private java.util.Date createdAt;

	@Column(name = "updated_at")
	private java.util.Date updatedAt;


	@OneToMany
	@JoinColumn(name="fun_id")
	private Set<FunImages> funImagesList;

	@OneToOne
	@JoinColumn(name="fun_id")
	private FunContent funContent;


	public Fun(Integer authority, String password, BigDecimal fee, Date createdAt, Date updatedAt) {
		this.authority = authority;
		this.password = password;
		this.fee = fee;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
