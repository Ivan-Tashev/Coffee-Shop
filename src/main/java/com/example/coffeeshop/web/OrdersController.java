package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.OrderAddBindModel;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public OrdersController(OrderService orderService, CategoryService categoryService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getAddOrderPage(Model model) {
        if (!model.containsAttribute("orderAddBindModel")) {
            model.addAttribute("orderAddBindModel", new OrderAddBindModel());
        }
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@Valid OrderAddBindModel orderAddBindModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindModel", orderAddBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindModel", bindingResult);
            return "redirect:/orders/add";
        }
        orderService.addOrder(modelMapper.map(orderAddBindModel, OrderServiceModel.class)
                .setCategoryEntity(categoryService.findByName(orderAddBindModel.getCategory()))
        );

        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteById(id);
        return "redirect:/home";
    }
}
