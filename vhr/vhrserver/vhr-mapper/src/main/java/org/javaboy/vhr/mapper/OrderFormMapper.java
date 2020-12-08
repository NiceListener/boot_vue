package org.javaboy.vhr.mapper;

import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.OrderForm;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: No Description
 * @Changelog: Here is the change log of the file
 * @Author: lisx
 * @CreateTime: 2020/12/7 9:32
 */
@Repository
public interface OrderFormMapper {
    List<Employee> getOrderListByPage(Integer page, Integer size, OrderForm orderForm, Date[] beginDateScope);

    Long getTotal(OrderForm orderForm, Date[] beginDateScope);

    OrderForm getOrderFormInfo(Long id);

    Integer addOrderForm(OrderForm orderForm);
}
