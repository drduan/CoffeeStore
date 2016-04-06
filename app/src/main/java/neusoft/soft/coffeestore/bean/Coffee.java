package neusoft.soft.coffeestore.bean;

import java.io.Serializable;

public class Coffee implements Serializable{
private Integer coffee_id;
private String coffee_name;
private float  coffee_price;
private String coffee_intro;
private String coffee_com;
private String image_name;
public Integer getCoffee_id() {
	return coffee_id;
}
public void setCoffee_id(Integer coffee_id) {
	this.coffee_id = coffee_id;
}
public String getCoffee_name() {
	return coffee_name;
}
public void setCoffee_name(String coffee_name) {
	this.coffee_name = coffee_name;
}
public float getCoffee_price() {
	return coffee_price;
}
public void setCoffee_price(float coffee_price) {
	this.coffee_price = coffee_price;
}
public String getCoffee_intro() {
	return coffee_intro;
}
public void setCoffee_intro(String coffee_intro) {
	this.coffee_intro = coffee_intro;
}
public String getCoffee_com() {
	return coffee_com;
}
public void setCoffee_com(String coffee_com) {
	this.coffee_com = coffee_com;
}
public String getImage_name() {
	return image_name;
}
public void setImage_name(String image_name) {
	this.image_name = image_name;
}

}
