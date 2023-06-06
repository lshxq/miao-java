package stian.miao.entity;

import java.util.Date;

public class AccessEntity {
	private String ip;
	private Date access;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getAccess() {
		return access;
	}
	public void setAccess(Date access) {
		this.access = access;
	}
}
