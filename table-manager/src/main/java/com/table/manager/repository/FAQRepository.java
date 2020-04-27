package com.table.manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.table.manager.model.FAQ;
import com.table.manager.model.User;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {
	public Page<FAQ> findByUser(User user, Pageable pageable);
}
