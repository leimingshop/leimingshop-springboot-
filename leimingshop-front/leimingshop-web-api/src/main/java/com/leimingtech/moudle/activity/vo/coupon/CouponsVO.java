/**
 *  http://www.leimingtech.com 雷铭科技
 */

package com.leimingtech.moudle.activity.vo.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品详情页-优惠券列表
 *
 * @author lixiangx@leimingtech.com
 * @date 2020/5/16 17:14
 **/
@Data
@ApiModel(value = "商品详情页-优惠券列表")
public class CouponsVO implements Serializable {

    private static final long serialVersionUID = -4730051770598812019L;

    @ApiModelProperty("优惠券ID")
    private Long id;

    @ApiModelProperty("店铺ID")
    private Long storeId;

    @ApiModelProperty("店铺名称")
    private String storeName;

    @ApiModelProperty(value = "店铺logo")
    private String storeLogo;

    @ApiModelProperty("优惠券类型 0满减券 1满折券")
    private Integer couponsType;

    @ApiModelProperty("使用限制金额")
    private BigDecimal limitAmount;

    @ApiModelProperty("优惠券面额")
    private BigDecimal faceValue;

    @ApiModelProperty("有效期类型 0固定时间 1有效天数")
    private Integer validityType;

    @ApiModelProperty("使用开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date useStartDate;

    @ApiModelProperty("使用结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date useEndDate;

    @ApiModelProperty("有效天数")
    private Integer validityDays;

    @ApiModelProperty("剩余数量")
    private Integer surplusNum;

    @ApiModelProperty(value = "每人限领数量")
    private Long personLimit;

    @ApiModelProperty("活动商品范围 0全场通用 1指定店铺分类 2指定商品 3指定品牌")
    private Integer activityGoodsScope;
}