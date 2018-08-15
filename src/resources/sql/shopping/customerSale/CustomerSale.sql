/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : นับจำนวนข้อมูล product
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
countProduct {
	SELECT COUNT(1) AS TOT
	FROM PRODUCT P
	INNER JOIN PRODUCT_TYPE PT ON P.TYPE_ID = PT.TYPE_ID
	WHERE P.TYPE_ID = %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ค้นหาข้อมูล product
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchProduct {
	SELECT
		P.ID
		, P.CODE
		, P.PRODUCT_DESC
		, P.PRICE
		, P.STOCK_NUM
		, CONCAT(PT.IMAGE_PATH, '\\', P.IMAGE_NAME) AS IMAGE
		, PT.TYPE_ID
		, PT.TYPE_DESC
		, PT.SEQ
	FROM PRODUCT P
	INNER JOIN PRODUCT_TYPE PT ON P.TYPE_ID = PT.TYPE_ID
	WHERE P.TYPE_ID = %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ค้นหาข้อมูล product ตาม id
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchProductById {
	SELECT
		P.ID
		, P.CODE
		, P.PRODUCT_DESC
		, P.PRICE
		, P.STOCK_NUM
		, P.IMAGE_NAME
		, PT.TYPE_ID
		, PT.TYPE_DESC
		, PT.SEQ
		, PT.IMAGE_PATH
	FROM PRODUCT P
	INNER JOIN PRODUCT_TYPE PT ON P.TYPE_ID = PT.TYPE_ID
	WHERE P.ID = %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : เพิ่มรายการสั่งซื้อ
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
insertOrderMain {
	INSERT INTO ORDER_MAIN (
	  TOTAL_PRICE
	  ,USER_ID
	  ,ORDER_DATE
	  ,SHIP
	  ,SHIP_DATE
	  ,TRACKING_NO
	  ,CANCEL
	  ,NOTE
	) VALUES (
		%s
	  , %s
	  , %s
	  , %s
	  , %s
	  , %s
	  , %s
	  , %s
	 )
}

insertOrderDetail {
	INSERT INTO ORDER_DETIL(
	  PRODUCT_ID
	  ,TOTAL_NUM
	  ,TOTAL_PRICE
	  ,ORDER_ID
	) VALUES (
		%s
	  , %s
	  , %s
	  , %s
	)
}
