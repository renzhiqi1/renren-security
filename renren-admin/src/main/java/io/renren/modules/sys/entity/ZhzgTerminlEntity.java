package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Rzq
 * @email ${email}
 * @date 2019-09-02 10:31:31
 */
@Data
@TableName("zhzg_terminl")
public class ZhzgTerminlEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 终端ID自增
	 */
	@TableId
	private Integer terminalId;
	/**
	 * 终端编号
	 */
	private Integer number;
	/**
	 * 终端IP
	 */
	private String terminalIp;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 所属部门
	 */
	private Long deptId;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	private Integer delFlag;
	/**
	 * 备注
	 */
	private String comment;

}
