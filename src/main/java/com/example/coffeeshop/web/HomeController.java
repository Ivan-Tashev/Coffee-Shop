package com.example.coffeeshop.web;

import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public HomeController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<OrderServiceModel> orders = orderService.getOrders();

        List<OrderViewModel> orderViewModels = orders.stream()
                .map(orderServiceModel -> modelMapper.map(orderServiceModel, OrderViewModel.class)
                        .setCategory(orderServiceModel.getCategoryEntity().getName())
                        .setEmployee(modelMapper.map(orderServiceModel.getEmployee(), UserServiceModel.class))
                )
                .sorted((a, b) -> b.getPrice().compareTo(a.getPrice()))
                .collect(Collectors.toList());
        model.addAttribute("orderViewModels", orderViewModels);

        Integer minutes = orders.stream()
                .map(orderServiceModel -> orderServiceModel.getCategoryEntity().getName().getMin())
                .reduce((a, b) -> a + b)
                .orElse(0);
        model.addAttribute("minutes", minutes);

        TreeMap<String, Integer> employeesOrders = new TreeMap<>();
        orders.forEach(orderServiceModel -> {
            employeesOrders.putIfAbsent(orderServiceModel.getEmployee().getLastName(), 0);
            employeesOrders.put(orderServiceModel.getEmployee().getLastName(),
                    employeesOrders.get(orderServiceModel.getEmployee().getLastName()) + 1);
        });
        model.addAttribute("employees",
                employeesOrders);
        return "home";
    }
}
