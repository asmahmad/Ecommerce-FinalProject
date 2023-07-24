package com.ecommerce.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.domain.Customer;
import com.ecommerce.domain.Product;
import com.ecommerce.domain.ShoppingCart;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.ShoppingCartService;

@Controller
public class CartController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ShoppingCartService cartService;

	@GetMapping("/cart")
	public String cart(Principal principal, Model model, HttpSession session) {
		if (principal == null) {

			return "redirect:/login";
		}
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		ShoppingCart shoppingCart = customer.getShoppingCart();
		if (shoppingCart == null) {
			model.addAttribute("check", "Cart is Empty");
		}
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("subTotal", shoppingCart.getTotalPrices());
		session.setAttribute("totalItems", shoppingCart.getTotalItems());

		return "cart";
	}

	@PostMapping("/add-to-cart")
	public String addItemToCart(@RequestParam("id") Long productId,
			@RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity, Principal principal,
			HttpServletRequest request) {

		if (principal == null) {

			return "redirect:/login";

		}
		Product product = productService.getProductById(productId);
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		ShoppingCart cart = cartService.addItemToCart(product, quantity, customer);

		return "redirect:" + request.getHeader("Referer");

	}

	@RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
	public String updateCart(@RequestParam("quantity") int quantity, @RequestParam("id") Long productId, Model model,
			Principal principal) {

		if (principal == null) {
			return "redirect:/login";
		} else {
			String username = principal.getName();
			Customer customer = customerService.findByUsername(username);
			Product product = productService.getProductById(productId);
			ShoppingCart cart = cartService.updateItemInCart(product, quantity, customer);

			model.addAttribute("shoppingCart", cart);
			return "redirect:/cart";
		}

	}

	@RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
	public String deleteItemFromCart(@RequestParam("id") Long productId, Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		} else {
			String username = principal.getName();
			System.out.println(username);
			Customer customer = customerService.findByUsername(username);
			
			Product product = productService.getProductById(productId);
			
			ShoppingCart cart = cartService.deleteItemFromCart(product, customer);
		
			model.addAttribute("shoppingCart", cart);
			return "redirect:/cart";
		}

	}
}