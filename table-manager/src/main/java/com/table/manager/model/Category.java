package com.table.manager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Setter
@Entity
@Table(name = "category", uniqueConstraints = @UniqueConstraint(columnNames = {"category_type", "category_name"}))
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	//포럼(1)의 카테고리인지 FAQ(2)의 카테고리인지
	@Column(name = "category_type", length = 100, nullable = false)
	private int categoryType;

	@Column(name = "category_name", nullable = false)
	private String categoryName;

	@Column(name = "color", length = 7)
	private String color;

	@Column(name="create_time", nullable = false)
	private Date createTime;

	@Column(name="update_time")
	private Date updateTime;
}
