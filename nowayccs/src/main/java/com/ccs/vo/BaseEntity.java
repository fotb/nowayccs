package com.ccs.vo;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 
 * @author Administrator
 * 
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4268686306902821501L;
	public final static int DELETE_FLAG_YES = 1 ;
	public final static int DELETE_FLAG_NO = 0 ;

    /**
     * 主键标示
     */
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(length=32)
	private String pid;
	
	@Temporal(TemporalType.TIMESTAMP)																																				
	@Column(name = "CREATETIME")
	private Date createTime;
	
	/**
	 * 删除标志  1已经删除，0 正常
	 */
	@Column(precision = 1,name ="DELETEFLAG")
	private Integer deleteFlag = DELETE_FLAG_NO ;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDT")
	private  Date updateDT  = new Date ();
	
	@Column(name = "LASTHANDLER")
	private String lastHandler;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getUpdateDT() {
		return updateDT;
	}

	public void setUpdateDT(Date updateDT) {
		this.updateDT = updateDT;
	}

	public String getLastHandler() {
		return lastHandler;
	}

	public void setLastHandler(String lastHandler) {
		this.lastHandler = lastHandler;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}
}