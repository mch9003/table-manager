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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "forum_comment")
public class ForumComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "depth", nullable = false)
	private int depth;

	@Column(name = "seq", nullable = false)
	private int seq;

	@Column(name = "content", length = 3000, nullable = false)
	private String content;

	@Column(name = "recommend")
	private int recommend;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id", unique = true)
	private User user;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "forum_id", unique = false)
	private Forum forum;

	@Column(name = "delete_yn")
	private boolean deleteYn;

	@Column(name="create_time", nullable = false)
	private Date createTime;

	@Column(name="update_time")
	private Date updateTime;

}
