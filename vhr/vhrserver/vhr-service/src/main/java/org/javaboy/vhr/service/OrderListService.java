package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.OrderFormMapper;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.OrderForm;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: No Description
 * @Changelog: Here is the change log of the file
 * @Author: lisx
 * @CreateTime: 2020/12/7 9:29
 */
@Service
public class OrderListService {

    @Autowired
    private OrderFormMapper orderFormMapper;

    public RespPageBean getOrderListByPage(Integer page, Integer size, OrderForm orderForm, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = orderFormMapper.getOrderListByPage(page, size, orderForm, beginDateScope);
        Long total = orderFormMapper.getTotal(orderForm, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public OrderForm getOrderFormInfo(Long id){
        OrderForm orderForm = orderFormMapper.getOrderFormInfo(id);
        return orderForm;
    }

    public Integer addOrderForm(OrderForm orderForm) {
        return orderFormMapper.addOrderForm(orderForm);
    }
}
