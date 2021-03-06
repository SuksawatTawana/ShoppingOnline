/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ตรวจสอบข้อมูลพนักงานซ้ำ_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
checkDupLogin {
	SELECT COUNT(1) AS TOT
	FROM USER
	WHERE LOGIN_NAME = %s
	AND PASSWORD = %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ค้นหาข้อมูลผู้ใช้งาน
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchUserLogin {
	SELECT
	  USER_ID
	  , FIRST_NAME
	  , CONCAT(FIRST_NAME, ' ', LAST_NAME) AS FULLNAME
	  , ADMIN
	FROM USER
	WHERE LOGIN_NAME = %s
	AND PASSWORD = %s
}