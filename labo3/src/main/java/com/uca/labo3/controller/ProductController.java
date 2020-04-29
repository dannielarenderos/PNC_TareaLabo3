package com.uca.labo3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uca.labo3.domain.Product;


@Controller
public class ProductController {

	private List<Product> productos = new ArrayList<Product>();
	
	@GetMapping("/compraProducto")
	public ModelAndView compraProducto() {
		ModelAndView mav = new ModelAndView();
		
		productos.add(new Product(0,"Camisa",30));
		productos.add(new Product(1,"Cartera",45));
		productos.add(new Product(2,"Jeans",54));
		productos.add(new Product(3,"Zapatos",25));
		productos.add(new Product(4,"Falda",68));
		productos.add(new Product(5,"Short",35));
		
		mav.setViewName("select");

		mav.addObject("product", new Product());
		mav.addObject("producto",productos);
		
		return mav;
	}
	
	

	@PostMapping("/validar")
	@ResponseBody
	public ModelAndView validar(Product product){
		
		String mensaje;
		ModelAndView mav = new ModelAndView();

		Integer cantDisponible= productos.get(product.getId()).getCantidad();
		Integer cantPedida = product.getCantidad();
		Integer resultado = cantDisponible-cantPedida;
		
		if(resultado>0) {
			
			mav.setViewName("compra");
			mensaje = "El producto " + productos.get(product.getId()).getNombre() +" se adquirió.";
			mav.addObject("mensaje",mensaje);
			return mav;
		}
		else {
			
			mav.setViewName("error");
			mensaje = "El producto " + productos.get(product.getId()).getNombre() +" no se puede adquirir.";
			mav.addObject("mensaje",mensaje);
			

			return mav;
		}
	}
}
