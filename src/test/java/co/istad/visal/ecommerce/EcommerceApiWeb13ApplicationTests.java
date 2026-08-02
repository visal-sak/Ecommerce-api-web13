package co.istad.visal.ecommerce;

import co.istad.visal.ecommerce.features.category.Category;
import co.istad.visal.ecommerce.features.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EcommerceApiWeb13ApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	//Test select data from category
	void test_selectCategory(){
		List<Category> categories = categoryRepository.findAll();

		IO.println("----<Category>------");
		categories.forEach(category -> {
			IO.println("ID:"+ category.getId());
			IO.println("Name:"+ category.getName());
			IO.println("Icon:"+ category.getIcon());
			IO.println("IsDeleted:"+ category.getIsDeleted());
			IO.println("----------");
		});
	}

   // Test save data in catagory data
	@Test
	void test_saveCategory(){
		// 1. create entity
		Category category = new Category();
		category.setName("Phone18pro");
		category.setIcon("default-iphone18pro.png");
		category.setIsDeleted(false);
		category.setDescription("my iphone 18 pro");
		categoryRepository.save(category);
	}


}
