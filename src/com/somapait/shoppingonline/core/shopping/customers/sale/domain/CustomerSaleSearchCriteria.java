package com.somapait.shoppingonline.core.shopping.customers.sale.domain;

import com.somapait.domain.SearchCriteria;

/**
 * @description Class สำหรับเก็บเงื่อนใขในการค้นหาจากหน้าหลัก มาจากการค้นหารายการสินค้าแต่ละประเภทที่เลือกจากเมนู
 * @author -
 */
public class CustomerSaleSearchCriteria extends SearchCriteria{

	private static final long serialVersionUID = -8712822027103424600L;
	private String typeId;
	private String typeDesc;
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
	

}
