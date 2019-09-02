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
@TableName("zhzg_inform")
public class ZhzgInformEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer informId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发布者
	 */
	private String publisher;
	/**
	 * 内容
	 */
	private String context;
	/**
	 * 发布时间
	 */
	private Date pubTime;
	/**
	 * 发布地点
	 */
	private String pubSite;
	/**
	 * 状态：0 禁用 1正常
	 */
	private Integer status;

}
