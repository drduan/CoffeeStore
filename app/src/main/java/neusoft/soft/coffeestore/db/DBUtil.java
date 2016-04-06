package neusoft.soft.coffeestore.db;
import neusoft.soft.coffeestore.bean.Coffee;
import neusoft.soft.coffeestore.bean.Shop;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
//Int imageId=this.getResources()getIdentifiier(this.getPackageName+”:drawable/”+imageFileName);

public class DBUtil {

	private final static String DB_NAME = "coffeeshop";
	private final static int DB_VERSION = 1;


	private final static String TABLE_SHOP = "shop";
	private final static String TABLE_COFFEE="recom_coffee";
	public static final String KEY_ID = "shop_id";
	public static final String KEY_NAME = "shop_name";
	public static final String KEY_ADDRESS = "shop_address";
	public static final String KEY_TELEPHONE = "tel";
	public static final String KEY_IMG_NAME = "img_name";
	private String databasePath;
	private SQLiteDatabase database;

	private static DBUtil dbUtil;

	private DBUtil(String databasePath){

	}

	public static DBUtil getInstance(String databasePath) {

		if(dbUtil == null){

			dbUtil = new DBUtil(databasePath);
		}
		dbUtil.databasePath = databasePath;
		return dbUtil;
	}

	public int openDB(){

		try {

			if(database == null || !database.isOpen()){

				database = SQLiteDatabase.openDatabase(this.databasePath, null, SQLiteDatabase.OPEN_READWRITE);
			}
		} catch (SQLiteException e) {

			return -1;
		}
		return 0;
	}

	public void closeDB(){

		if(database != null && database.isOpen()){

			database.close();
			database = null;
		}
	}
	public long deleteOneData(String id) {
		return database.delete(TABLE_SHOP,  "shop_id" + "=" +"'"+ id+"'", null);
	}
	public Shop[] queryAllShop() {
		Cursor results =  database.query(TABLE_SHOP, null,
				null, null, null, null, null);
		return ConvertToShop(results);
	}
	public Coffee[] queryAllCoffee() {
		Cursor results =  database.query(TABLE_COFFEE, null,
				null, null, null, null, null);
		return ConvertToCoffee(results);
	}
	private Coffee[] ConvertToCoffee(Cursor cursor){
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()){
			return null;
		}
		Coffee[] coffees = new Coffee[resultCounts];
		for (int i = 0 ; i<resultCounts; i++){
			coffees[i] = new Coffee();
			coffees[i].setCoffee_id(cursor.getInt(0));
			coffees[i].setCoffee_name(cursor.getString(cursor.getColumnIndex("coffee_name")));
			coffees[i].setCoffee_price(cursor.getInt(2));
			coffees[i].setCoffee_intro(cursor.getString(3));
			coffees[i].setCoffee_com(cursor.getString(4));
			coffees[i].setImage_name(cursor.getString(5));
			cursor.moveToNext();
		}
		return coffees;
	}


	private Shop[] ConvertToShop(Cursor cursor){
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()){
			return null;
		}
		Shop[] shops = new Shop[resultCounts];
		for (int i = 0 ; i<resultCounts; i++){
			shops[i] = new Shop();
			shops[i].setShop_id(cursor.getString(0)) ;
			shops[i].setShop_name(cursor.getString(cursor.getColumnIndex("shop_name")));
			shops[i].setShop_address(cursor.getString(2));
			shops[i].setTel(cursor.getString(3));
			shops[i].setImg_name(cursor.getString(4));
			shops[i].setImg_id(cursor.getInt(5));
			cursor.moveToNext();
		}
		return shops;
	}

	public  Cursor getShopLike(Shop shop){

		String sql="select * from "+TABLE_SHOP +" where shop_name like '%"+shop.getShop_name()+"%'";

		Cursor results =  database.rawQuery(sql, null);
		//select * from shop where shop_name like '%左%'
		/*Cursor cursor = null;
		if(shop != null){

			return database.query(TABLE_SHOP, null, "shop_name like '%?"+"%'", new String[]{shop.getShop_name()}, null, null, null);
		}*/
		return results;
	}
	public  Cursor getShop(Shop shop){

		Cursor cursor = null;
		if(shop != null){
			//select * from shop where shop_id='LN_DL_ZS_001' and shop_name='左岸咖啡店'
			return database.query(TABLE_SHOP, null, "shop_id = ?"+" and "+"shop_name=?", new String[]{shop.getShop_id(),shop.getShop_name()}, null, null, null);
		}
		return cursor;
	}
	public Cursor getAllShop(){
		return database.query(TABLE_SHOP, null,null,null,null,null,null);
	}
	public long insert(Shop shop) {
		ContentValues newValues = new ContentValues();

		newValues.put(KEY_ID, shop.getShop_id());
		newValues.put(KEY_NAME, shop.getShop_name());
		newValues.put(KEY_ADDRESS, shop.getShop_address());
		newValues.put(KEY_TELEPHONE, shop.getTel());
		newValues.put(KEY_IMG_NAME, shop.getImg_name());
		return database.insert( TABLE_SHOP, null, newValues);
	}
	public long deleteAllData() {
		return database.delete(TABLE_SHOP, null, null);
	}



	public long updateOneData(String id ,Shop shop){
		ContentValues updateValues = new ContentValues();
		updateValues.put(KEY_NAME, shop.getShop_name());
		updateValues.put(KEY_ADDRESS, shop.getShop_address());
		updateValues.put(KEY_TELEPHONE, shop.getTel());
		updateValues.put(KEY_IMG_NAME, shop.getImg_name());

		return database.update(TABLE_SHOP, updateValues,  KEY_ID + "=" +"'"+ id+"'", null);
	}




}
