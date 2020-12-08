package org.javaboy.vhr.controller.orderForm;

import org.javaboy.vhr.model.OrderForm;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: No Description
 * @Changelog: Here is the change log of the file
 * @Author: lisx
 * @CreateTime: 2020/12/4 16:50
 */
@RestController
@RequestMapping("/order/basic")
public class OrderListController {
    @Autowired
    private OrderListService orderListService;

    @GetMapping("/")
    public RespPageBean getOrderListByPage(Integer page, Integer size, OrderForm orderForm, Date[] beginDateScope) {
        return orderListService.getOrderListByPage(page, size, orderForm, beginDateScope);
    }

    @PostMapping("/orderFormInfo")
    public OrderForm getOrderFormInfo(Long id) {
        return orderListService.getOrderFormInfo(id);
    }

    @PostMapping("/addOrderForm")
    public RespBean addOrderForm(@RequestBody OrderForm orderForm) {
        if (orderListService.addOrderForm(orderForm) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
}
