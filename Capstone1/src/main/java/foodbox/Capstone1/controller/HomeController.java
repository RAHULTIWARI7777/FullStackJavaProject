package foodbox.Capstone1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import foodbox.Capstone1.global.GlobalData;
import foodbox.Capstone1.model.Product;
import foodbox.Capstone1.repository.CategoryRepository;
import foodbox.Capstone1.repository.ProductRepository;
import foodbox.Capstone1.service.CategoryService;
import foodbox.Capstone1.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping({ "/", "/home" })
	public String viewindexpage(Model model , @Param("keyword") String keyword ) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		
		List<Product> products  =  productService.findAllthrough(keyword);
		model.addAttribute("products",products);

		return "index";
	}

	@GetMapping("/shop")
	public String viewfoodpage(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		
		model.addAttribute("categories", categoryService.getAllcategories());
		model.addAttribute("products", productService.getAllproducts());
		return "shop";
	}

	@GetMapping("/shop/category/{id}")

	public String viewfoodpagebyid(@PathVariable(value = "id") int id, Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getAllcategories());
		model.addAttribute("products", productService.getAllproductbycategoryId(id));
		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String viewfoodcartbyid(@PathVariable(value = "id") int id, Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());

		model.addAttribute("product", productService.getProductByid(id).get());
		return "viewProduct";
	}
}
