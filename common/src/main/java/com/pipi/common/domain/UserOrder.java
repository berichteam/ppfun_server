package com.pipi.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.pipi.common.enums.ProductType;
import com.pipi.common.enums.SocialType;
import com.pipi.common.enums.TradeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

/**
* Created by Mybatis Generator 2019/06/20
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserOrder implements Serializable {
    private Long id;

    private Integer productType;// 商品类型枚举，1礼物，2vip

    private Long productId;// 商品id

    private Long userId;// 用户id

    private String tradeNo;// 内部交易唯一流水

    private BigDecimal totalFee;// 总金额

    private Integer tradeChannel;// 交易渠道

    private Integer productAmount;// 商品数量

    private String productNo;// 商品渠道编号

    private String tradeType;// 交易类型，预下单成功后返回

    private String prepayId;// 预交易id，预交易成功后返回

    private String tradeState;// 交易状态，查询订单时返回

    private Integer tradeStatus;// 交易状态枚举

    private String transactionId;// 交易id，交易成功后返回

    private String bankType;// 银行类别，交易成功后返回

    private Date createdAt;

    private Date payAt;// 支付时间

    private Date finshAt;// 完成时间

    private Date updatedAt;

    public UserOrder(ProductType productType, Long productId, Long userId, String tradeNo, BigDecimal totalFee, SocialType socialType,
                     Integer productAmount) {
        this.productType = productType.code();
        this.productId = productId;
        this.userId = userId;
        this.tradeNo = tradeNo;
        this.totalFee = totalFee;
        this.tradeChannel = socialType.ordinal();
        this.productAmount = productAmount;
        this.tradeStatus = TradeStatus.UNPAY.ordinal();
        this.createdAt = DateTime.now().toDate();
        this.updatedAt = DateTime.now().toDate();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productType=").append(productType);
        sb.append(", productId=").append(productId);
        sb.append(", userId=").append(userId);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", tradeChannel=").append(tradeChannel);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", productNo=").append(productNo);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", prepayId=").append(prepayId);
        sb.append(", tradeState=").append(tradeState);
        sb.append(", tradeStatus=").append(tradeStatus);
        sb.append(", transactionId=").append(transactionId);
        sb.append(", bankType=").append(bankType);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", payAt=").append(payAt);
        sb.append(", finshAt=").append(finshAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}