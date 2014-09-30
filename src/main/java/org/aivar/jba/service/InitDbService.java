package org.aivar.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.aivar.jba.entity.Blog;
import org.aivar.jba.entity.Item;
import org.aivar.jba.entity.Role;
import org.aivar.jba.entity.User;
import org.aivar.jba.repository.BlogRepository;
import org.aivar.jba.repository.ItemRepository;
import org.aivar.jba.repository.RoleRepository;
import org.aivar.jba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InitDbService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setName("admin");
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		Blog blogJavavids = new Blog();
		blogJavavids.setName("JavaVids");
		blogJavavids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJavavids.setUser(userAdmin);
		blogRepository.save(blogJavavids);

		Item item1 = new Item();
		item1.setTitle("first");
		item1.setLink("http://www.google.ee");
		item1.setPublishedDate(new Date().toString());
		itemRepository.save(item1);

		Item item2 = new Item();
		item2.setTitle("second");
		item2.setLink("http://www.google.ee");
		item2.setPublishedDate(new Date().toString());
		itemRepository.save(item2);
	}
}
