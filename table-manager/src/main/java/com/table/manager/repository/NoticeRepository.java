package com.table.manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.table.manager.model.Notice;
import com.table.manager.model.User;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
	public Page<Notice> findByUser(User user, Pageable pageable);
}
