package com.table.manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.table.manager.model.Category;
import com.table.manager.model.Forum;
import com.table.manager.model.User;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {
	@Query()
	public Page<Forum> findByHashTag(String hashTag, Pageable pageable);
	public Page<Forum> findByCategory(Category category, Pageable pageable);
	public Page<Forum> findByUser(User user, Pageable pageable);
}
