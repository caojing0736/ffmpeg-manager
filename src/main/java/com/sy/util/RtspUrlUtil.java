package com.sy.util;

import com.sy.entity.pojo.CamaraInfo;
import com.sy.core.excpetion.ParamException;

/**
 * 组装rtsp拉流url
 * @author jack
 *
 */
public class RtspUrlUtil {

	/**
	 * 组装rtsp拉流url
	 * @param camaraInfo
	 * @return
	 * @throws ParamException
	 */
	public static String camaraInfo2Url(CamaraInfo camaraInfo) throws ParamException {
		
		if(camaraInfo == null) throw new ParamException("camaraInfo 不能为空");
		
		//"rtsp://admin:sy123456@192.168.1.63/h264/1/main/av_stream"
		StringBuilder builder = new StringBuilder();
		builder.append("rtsp://")
				.append(camaraInfo.getVarCameraUserName())
				.append(":")
				.append(camaraInfo.getVarCameraPWD())
				.append("@")
				.append(camaraInfo.getVarCameraAddr())
				.append("/h264/")
				.append(camaraInfo.getVarChannelNo())
				.append("/main/av_stream");
		
		return builder.toString();
	}
}
