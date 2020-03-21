package com.sy.util;


import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sy.core.excpetion.BusException;
import com.sy.entity.PropertiesObject;
import com.sy.entity.pojo.CamaraInfo;
import com.sy.hkwsSDK.HCNetSDK;


public class HKSDKUtil {

	
	private static String capturePicturePath = SpringContextHolder.getBean(PropertiesObject.class).getCapturePicturePath();
	
	public static String captureJPEGPicture(CamaraInfo camaraInfo) throws BusException {
		
		NativeLong chanLong = new NativeLong(1);

		HCNetSDK sdk = HCNetSDK.INSTANCE;
		if(!sdk.NET_DVR_Init()) {
			throw new BusException("SDK初始化失败，请联系管理员或供应商！");
		}
		
		//注册设备
		HCNetSDK.NET_DVR_DEVICEINFO_V30 devinfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		NativeLong userID = sdk.NET_DVR_Login_V30(camaraInfo.getVarCameraAddr() , camaraInfo.getVarCameraPort(),
				camaraInfo.getVarCameraUserName(), camaraInfo.getVarCameraPWD(), devinfo);
		
		if (userID.intValue() < 0) {
			LoggerUtil.info("设备注册失败，返回错误码：" + sdk.NET_DVR_GetLastError());
			
			throw new BusException("设备注册失败，请检查：1、摄像头是否正常；2、参数是否正确");
			}
		
		//创建图片目录 /usr/local/src/app_server/pictures/ + 序列号 + yyyy-MM-dd HH:mm:ss .jpg
		String fileNameString;
		

		try {
			// 返回Boolean值，判断是否获取设备能力
			HCNetSDK.NET_DVR_WORKSTATE_V30 devwork = new HCNetSDK.NET_DVR_WORKSTATE_V30();
			if (!sdk.NET_DVR_GetDVRWorkState_V30(userID, devwork)) {
				throw new BusException("设备不支持此操作，请联系管理员或供应商！");
			}
			//JPEG图像信息结构体
			HCNetSDK.NET_DVR_JPEGPARA jpeg = new HCNetSDK.NET_DVR_JPEGPARA();
			jpeg.wPicSize = 2;// 设置图片的分辨率
			jpeg.wPicQuality = 2;// 设置图片质量
			//设置图片大小
			IntByReference a = new IntByReference();
			fileNameString = capturePicturePath + camaraInfo.getVarTaskID() + /*DateUtil.getDate2() +*/ ".jpg";
			//抓图到文件
			boolean success = sdk.NET_DVR_CaptureJPEGPicture(userID, chanLong, jpeg, fileNameString);
			if (success) {

				LoggerUtil.info("抓取成功,返回长度：" + a.getValue());

			} else {

				throw new BusException("设备注册失败，返回错误码：" + sdk.NET_DVR_GetLastError() + "，请联系管理员或供应商！");

			} 
		} finally {
			sdk.NET_DVR_Logout(userID);
			sdk.NET_DVR_Cleanup();
		}
		
		
		
		return fileNameString;
	}
	
}
