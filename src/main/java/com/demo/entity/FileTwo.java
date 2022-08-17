package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="File2")
public class FileTwo 
{	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String col5;
	private String col6;
	private String col7;
	private String col8;
	private String col9;
	public String getCol9() {
		return col9;
	}
	public void setCol9(String col9) {
		this.col9 = col9;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCol5() {
		return col5;
	}
	public void setCol5(String col5) {
		this.col5 = col5;
	}
	public String getCol6() {
		return col6;
	}
	public void setCol6(String col6) {
		this.col6 = col6;
	}
	public String getCol7() {
		return col7;
	}
	public void setCol7(String col7) {
		this.col7 = col7;
	}
	public String getCol8() {
		return col8;
	}
	public void setCol8(String col8) {
		this.col8 = col8;
	}
	@Override
	public String toString() {
		return "FileTwo [id=" + id + ", col5=" + col5 + ", col6=" + col6 + ", col7=" + col7 + ", col8=" + col8
				+ ", col9=" + col9 + "]";
	}
	
}
