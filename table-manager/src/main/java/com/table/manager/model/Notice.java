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
@Table(name = "notice")
public class Notice extends Pageable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column(name = "hit")
	private int hit;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id", unique = true, referencedColumnName = "id")
	private User user;

	@Column(name = "delete_yn")
	private boolean deleteYn;

	@Column(name = "popup_deadline")
	private Date popupDeadline;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="update_time")
	private Date updateTime;
}
