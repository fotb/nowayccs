package com.ccs.vo;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


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
	@GeneratedValue(generator = "_increment")
	@GenericGenerator(name = "_increment", strategy = "increment")
	@Column(length=32)
	private Integer pid;
	
	/**
	 * 删除标志  1已经删除，0 正常
	 */
	@Column(precision = 1,name ="DELETE_FLAG")
	private Integer deleteFlag = DELETE_FLAG_NO ;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DT")
	private  Date updateDT  = new Date ();
	
	@Column(name = "LAST_HANDLER")
	private String lastHandler;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
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
	
}