package com.table.manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.table.manager.model.Forum;
import com.table.manager.model.ForumComment;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
	@Query("SELECT c FROM ForumComment c WHERE c.forum = ?1 "
			+ "ORDER BY coalesce(c.parentId, c.id) DESC, c.seq ASC")
	public Page<ForumComment> findByForum(Forum forum, Pageable pageable);
}
