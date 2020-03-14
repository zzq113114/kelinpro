/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/*****************************************************************************
 *创建时间 : 2019年04月08日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年04月08日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public class TbServiceItemConfig implements Serializable {

	// 编号
	private Integer id;

	// 起订
	private Short bookCount;

	// 单价
	private BigDecimal unitPrice;

	// 单位
	private String unit;

	// 售价
	private BigDecimal salePrice;

	// 总时长
	private Float totalTime;

	// 总人数
	private Short pCount;

	// 开始时长区间
	private Short timeRangeStart;

	// 结束时间区间
	private Short timeRangeEnd;

	// 开始价格区间
	private BigDecimal priceRangeStart;

	// 结束价格区间
	private BigDecimal priceRangeEnd;

	// 保留字段1
	private String reservedField1;

	// 保留字段2
	private String reservedField2;

	// 保留字段3
	private String reservedField3;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getBookCount() {
		return this.bookCount;
	}

	public void setBookCount(Short bookCount) {
		this.bookCount = bookCount;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Float getTotalTime() {
		return this.totalTime;
	}

	public void setTotalTime(Float totalTime) {
		this.totalTime = totalTime;
	}

	public Short getpCount() {
		return this.pCount;
	}

	public void setpCount(Short pCount) {
		this.pCount = pCount;
	}

	public Short getTimeRangeStart() {
		return this.timeRangeStart;
	}

	public void setTimeRangeStart(Short timeRangeStart) {
		this.timeRangeStart = timeRangeStart;
	}

	public Short getTimeRangeEnd() {
		return this.timeRangeEnd;
	}

	public void setTimeRangeEnd(Short timeRangeEnd) {
		this.timeRangeEnd = timeRangeEnd;
	}

	public BigDecimal getPriceRangeStart() {
		return this.priceRangeStart;
	}

	public void setPriceRangeStart(BigDecimal priceRangeStart) {
		this.priceRangeStart = priceRangeStart;
	}

	public BigDecimal getPriceRangeEnd() {
		return this.priceRangeEnd;
	}

	public void setPriceRangeEnd(BigDecimal priceRangeEnd) {
		this.priceRangeEnd = priceRangeEnd;
	}

	public String getReservedField1() {
		return this.reservedField1;
	}

	public void setReservedField1(String reservedField1) {
		this.reservedField1 = reservedField1;
	}

	public String getReservedField2() {
		return this.reservedField2;
	}

	public void setReservedField2(String reservedField2) {
		this.reservedField2 = reservedField2;
	}

	public String getReservedField3() {
		return this.reservedField3;
	}

	public void setReservedField3(String reservedField3) {
		this.reservedField3 = reservedField3;
	}
}
