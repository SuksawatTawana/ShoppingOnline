package com.somapait.shoppingonline.core.shopping.admin.sale.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.somapait.abstracts.AbstractManager;
import com.somapait.common.CommonSelectItem;
import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSale;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearch;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearchCriteria;
import util.log.LogUtil;

/**
 * @description Class สำหรับจัดการการทำงานต่างๆ แยกตาม module
 * @author -
 *
 */
public class AdminSaleManager extends AbstractManager<AdminSaleSearchCriteria, AdminSaleSearch, AdminSale, CommonUser, Locale>{

	private AdminSaleService service;
	
	public AdminSaleManager(Connection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		service = new AdminSaleService(conn, user, locale);
	}

	//TODO method search(AdminSaleSearchCriteria criteria) สำหรับค้นหารายการสั่งซื้อทั้งหมด ตามเงื่อนไขที่ได้รับจากหน้าจอ
	@Override
	public List<AdminSaleSearch> search(AdminSaleSearchCriteria criteria) throws Exception {
		List<AdminSaleSearch> listResult = new ArrayList<AdminSaleSearch>();
		try{
			criteria.setTotalResult(service.countData(conn, criteria, user, locale));
			LogUtil.SELECTOR.debug("COUNT DATA [" + criteria.getTotalResult() + "] record.");
			
			if (criteria.getTotalResult() == 0) {
				// Nothing
			} else {
	        	// ค้นหาข้อมูล
	        	listResult = service.search(conn, criteria, user, locale);
	        }
		}catch (Exception e) {
			LogUtil.SELECTOR.debug("Eror : ",e);
			throw e;
		}
		return listResult;
	}

	//TODO method searchById(String id) สำหรับไปค้นหารายการสั่งซื้อที่เลือกจากหน้าค้นหา แล้วนำไปแสดง
	@Override
	public AdminSale searchById(String id) throws Exception {
		AdminSale result = new AdminSale();
		try {
			result = service.searchById(conn, id, user, locale);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int add(AdminSale obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	//TODO method edit(AdminSale adminSale) สำหรับจัดส่ง
	@Override
	public int edit(AdminSale obj) throws Exception {
		
		if(obj.getOrderMain().getId() == "0" || obj.getOrderMain().getId() == ""){
			return 0;
		}
		
		int id = 0;
		try {
	        //2.Begin transaction
	        conn.setAutoCommit(false);
	 
	        //3.แก้ไขข้อมูลผู้ใช้งาน
	        id = service.edit(conn, obj, user, locale);

	        conn.commit();
	 
	    } catch (Exception e) {
	    	LogUtil.SELECTOR.debug("Eror : ",e);
	        conn.rollback();
	        throw e;
	    } finally {
	        conn.setAutoCommit(true);
	    }
	    return id;
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	//TODO method updateActive(String ids, String activeFlag) สำหรับยกเลิกรายการสั่งซื้อที่เลือกจากหน้าค้นหา

	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		try {
	        return service.updateActive(conn, ids, activeFlag);
	    } catch (Exception e) {
	        throw e;
	    }
	}

	public List<CommonSelectItem> getListShip(){
		List<CommonSelectItem> listShip = new ArrayList<CommonSelectItem>();
		try{
			CommonSelectItem comm1 = new CommonSelectItem();
			comm1.setKey("Y");
			comm1.setValue("จัดส่งแล้ว");
			listShip.add(comm1);
			
			CommonSelectItem comm2 = new CommonSelectItem();
			comm2.setKey("N");
			comm2.setValue("ยังไม่ได้จัดส่ง");
			listShip.add(comm2);
		}catch (Exception e) {
			
		}
		return listShip;
	}

	


	


	
	

}
