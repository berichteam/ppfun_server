package com.pipi.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "fun_content")
public class FunContent  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fun_id")
	private Integer funId;

	private String title;

	private String content;

	private Integer authority;

	private String password;

	@Column(name = "created_at")
	private java.util.Date createdAt;

	@Column(name = "update_at")
	private java.util.Date updateAt;

	public FunContent(Integer funId, String title, String content, Date createdAt, Date updateAt) {
		this.funId = funId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
}
