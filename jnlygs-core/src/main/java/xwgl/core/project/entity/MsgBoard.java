package xwgl.core.project.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xwgl.common.entity.BaseEntity;
@Entity
@Table(name = "t_msgboard")
public class MsgBoard extends BaseEntity<Long> implements Serializable{
	
	@ManyToOne(fetch = FetchType.EAGER)
	private MsgBoard replyfor;
	
	private String msg;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private FrontUser user;
	
	
	
	public void setReplyfor(MsgBoard replyfor) {
		this.replyfor = replyfor;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public FrontUser getUser() {
		return user;
	}
	public void setUser(FrontUser user) {
		this.user = user;
	}
	public MsgBoard getReplyfor() {
		return replyfor;
	}
}
