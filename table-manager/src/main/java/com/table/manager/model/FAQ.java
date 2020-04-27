package com.table.manager.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Setter
@Entity
@Table(name = "faq")
public class FAQ extends Pageable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "category_id", unique = true, referencedColumnName = "id")
	private Category category;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id", unique = true, referencedColumnName = "id")
	private User user;

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "forum", targetEntity = Comment.class)
//	private Collection<Comment> comment;

	@Column(name = "delete_yn")
	private boolean deleteYn;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="update_time")
	private Date updateTime;
}
