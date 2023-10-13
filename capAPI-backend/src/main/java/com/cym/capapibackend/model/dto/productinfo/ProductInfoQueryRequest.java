package com.cym.capapibackend.model.dto.productinfo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;

/**
 * @Author: Cap
 * @Date: 2023/08/25 03:12:57
 * @Version: 1.0
 * @Description: 查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductInfoQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品名称
     */
    private String name;
    /**
     * 增加积分个数
     */
    private Integer addPoints;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 金额(分)
     */
    private Integer total;

    /**
     * 产品类型（VIP-会员 RECHARGE-充值）
     */
    private String productType;

}