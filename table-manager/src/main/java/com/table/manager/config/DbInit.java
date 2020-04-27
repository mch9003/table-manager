package com.table.manager.config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.table.manager.model.Category;
import com.table.manager.model.Forum;
import com.table.manager.model.ForumComment;
import com.table.manager.model.User;
import com.table.manager.repository.CategoryRepository;
import com.table.manager.repository.ForumCommentRepository;
import com.table.manager.repository.ForumRepository;
import com.table.manager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DbInit implements CommandLineRunner {

	private final UserRepository userRepository;
	private final ForumRepository forumRepository;
	private final ForumCommentRepository commentRepository;
	private final CategoryRepository categoryRepository;
	//private final HashTagRepository hashTagRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// Delete all
		//this.hashTagRepository.deleteAll();
		//System.out.println("deleteALL1");
		this.commentRepository.deleteAll();
		System.out.println("deleteALL2");
		this.forumRepository.deleteAll();
		System.out.println("deleteALL3");
		this.categoryRepository.deleteAll();
		System.out.println("deleteALL4");
		this.userRepository.deleteAll();
		System.out.println("deleteALL5");
		System.out.println(categoryRepository.findAll());

		// create users
		User admin = new User("login_admin", passwordEncoder.encode("poiu098)(*"), "ADMIN",
				"ACCESS_TEST1,ACCESS_TEST2");
		admin.setCreateTime(new Date());
		admin.setLastLoginTime(new Date());
		User minho = new User("user", passwordEncoder.encode("user"), "USER", "");
		minho.setCreateTime(new Date());
		minho.setLastLoginTime(new Date());
		User manager = new User("manager", passwordEncoder.encode("manager123"), "MANAGER", "ACCESS_TEST1");
		manager.setCreateTime(new Date());
		manager.setLastLoginTime(new Date());

		List<User> users = Arrays.asList(minho, admin, manager);

		// save to db
		this.userRepository.saveAll(users);
		System.out.println("userRepository");

		Category category1 = new Category();
		category1.setCategoryType(1);
		category1.setCategoryName("일반");
		category1.setCreateTime(new Date());
		Category category2 = new Category();
		category2.setCategoryType(1);
		category2.setCategoryName("API 관련");
		category2.setCreateTime(new Date());

		List<Category> categoryList = categoryRepository.saveAll(Arrays.asList(category1, category2));
		//List<Category> categoryList = categoryRepository.findAll();
		System.out.println("categoryRepository"+categoryList);

		Forum forum = new Forum();
		//List<HashTag> hashTagList = saveHashTag("#aaa#bbb");
		//forum.setHashTag(hashTagList);
		forum.setHashTag("#aaa#bbb");
		forum.setTitle("title1");
		forum.setContent("aaaa");
		forum.setCategory(categoryList.get(0));
		forum.setUser(admin);
		forum.setCreateTime(new Date());
		forumRepository.save(forum);
		System.out.println("forumRepository");

		ForumComment comm1 = new ForumComment();
		comm1.setContent("fff");
		comm1.setDepth(1);
		comm1.setSeq(1);
		comm1.setUser(minho);
		comm1.setForum(forum);
		comm1.setCreateTime(new Date());
		comm1 = commentRepository.save(comm1);
		System.out.println("comm1");

		ForumComment comm3 = new ForumComment();
		comm3.setDepth(1);
		comm3.setSeq(1);
		comm3.setContent("zzz");
		comm3.setForum(forum);
		comm3.setUser(admin);
		comm3.setCreateTime(new Date());
		comm3 = commentRepository.save(comm3);
		System.out.println("comm3");

		ForumComment comm2 = new ForumComment();
		comm2.setParentId(comm3.getId());
		comm2.setDepth(2);
		comm2.setSeq(2);
		comm2.setContent("ddd");
		comm2.setForum(forum);
		comm2.setUser(manager);
		comm2.setCreateTime(new Date());
		commentRepository.save(comm2);
		System.out.println("comm2");



//		Order order = Order.by("parentId").with(Direction.DESC);
//		Order order2 = Order.by("seq").with(Direction.ASC);
//		Sort sort = Sort.by(Arrays.asList(order, order2));
		PageRequest pageRequest = PageRequest.of(0, 10);


		//System.out.println(commentRepository.findByForum(forum));
		System.out.println(commentRepository.findByForum(forum, pageRequest).getContent());

		System.out.println(categoryRepository.findByCategoryTypeAndCategoryName(1, "일반"));
		System.out.println(forumRepository.findAll());
		//System.out.println(forumRepository.findByHashTag(new HashTag() {{setId((long) 6);}}));
		System.out.println(forumRepository.findByCategory(new Category() {{setId((long) 4);}}, pageRequest));

	}

	/*
	private List<HashTag> saveHashTag(String hashTagStr) {
		System.out.println(hashTagStr);
		String[] hashTagArray = hashTagStr.split("#");
		List<HashTag> hashTagList = new ArrayList<HashTag>();
		System.out.println(hashTagArray[0]);
		for (int i = 1; i < hashTagArray.length; i++) {
			HashTag hashTag = hashTagRepository.findByTagName(hashTagArray[i].trim());
			if (hashTag != null) {
				hashTag.setCount(hashTag.getCount() + 1);
			} else {
				hashTag = new HashTag();
				hashTag.setTagName(hashTagArray[i].trim());
			}
			hashTag.setCreateTime(new Date());
			System.out.println(hashTag);
			hashTagList.add(hashTag);
		}
		return hashTagRepository.saveAll(hashTagList);
	}
	*/
}