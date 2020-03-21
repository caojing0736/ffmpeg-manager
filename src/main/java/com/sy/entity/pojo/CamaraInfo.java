package com.sy.entity.pojo;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CamaraInfo {

	@NotNull(message = "varTaskID不能为空")
	private String varTaskID;
	
	@NotNull(message = "varCameraUserName不能为空")
	private String varCameraUserName;
	
	@NotNull(message = "varCameraPWD不能为空")
	private String varCameraPWD;
	
	@NotNull(message = "varCameraAddr不能为空")
	private String varCameraAddr;
	
	private short varCameraPort;//服务端口
	
	private String varChannelNo = "1";//通道号
	
	private String resolution = "640x360";//分辨率
	
	private String vcodec = "h264";//编码
	
	private String tsformat = "flv";//转换格式
	
	private String frameSpaceString = "25";//帧间隔
	
	private String twoPart = "1";//0：元码流，1：自定义；2：多输出
	
}
