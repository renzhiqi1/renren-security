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
 * @date 2019-09-02 10:31:30
 */
@Data
@TableName("zhzg_plan")
public class ZhzgPlanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 计划ID
	 */
	@TableId
	private Long planId;
	/**
	 * 父计划ID，一级计划为0
	 */
	private Long parentId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发布人
	 */
	private String publisher;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 状态（启动、执行、暂定、结束、删除）
	 */
	private String state;
	/**
	 * 进度
	 */
	private String schedules;
	/**
	 * 发布时间
	 */
	private Date time;
	/**
	 * 审核标识（0 ：待审核 1:审核通过 2：审核不通过）
	 */
	private Integer auditFlag;
	/**
	 * 删除标识（0 禁用 1正常）
	 */
	private Integer deleFlag;
	/**
	 * 备注信息
	 */
	private String comment;

}
