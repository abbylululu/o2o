package com.lululu.O2O.entity;

import java.util.Date;

public class WechatAuth {

	private Long wecharAuthId;
	
	private String openId;
	
	private Date createTime;
	
	private PersonInfo person;

	public Long getWecharAuthId() {
		return wecharAuthId;
	}

	public void setWecharAuthId(Long wecharAuthId) {
		this.wecharAuthId = wecharAuthId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonInfo getPerson() {
		return person;
	}

	public void setPerson(PersonInfo person) {
		this.person = person;
	}
	
}
