package com.sy.hkwsSDK;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;


public class ImageTest1 {

	static String ipString = "192.168.1.63";
	static short portString = 8000;
	static String username = "admin";
	static String pwd = "sy123456";
	
	public static void main(String[] args) {
		getDVRPic();
	}
	
	
	public static String getDVRPic() {
		NativeLong chanLong = new NativeLong(1);


		HCNetSDK sdk = HCNetSDK.INSTANCE;
		if(!sdk.NET_DVR_Init()) {
			System.out.println("SDK初始化失败");
			return null;
		}
		
		//注册设备
		HCNetSDK.NET_DVR_DEVICEINFO_V30 devinfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		NativeLong id = sdk.NET_DVR_Login_V30(ipString , portString,
		username, pwd, devinfo);
		
		if (id.intValue() < 0) {
			System.out.println("设备注册失败"+sdk.NET_DVR_GetLastError());
			return null;
			} else {
			System.out.println("id：" + id);
			}
		
		// 返回Boolean值，判断是否获取设备能力
		HCNetSDK.NET_DVR_WORKSTATE_V30 devwork = new HCNetSDK.NET_DVR_WORKSTATE_V30();
		if (!sdk.NET_DVR_GetDVRWorkState_V30(id, devwork)) {
		System.out.println("返回设备状态失败");
		}

		//JPEG图像信息结构体
		HCNetSDK.NET_DVR_JPEGPARA jpeg = new HCNetSDK.NET_DVR_JPEGPARA();
		jpeg.wPicSize = 2;// 设置图片的分辨率
		jpeg.wPicQuality = 2;// 设置图片质量

		//设置图片大小
		IntByReference a = new IntByReference();
		
		//创建图片目录
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		int random = (int)(Math.random()*1000);
		String fileNameString = "/tmp/image/" +random + ".jpg";

		//设置字节缓存
		ByteBuffer jpegBuffer = ByteBuffer.allocate(1024 * 1024);

		//抓图到文件
		boolean is = sdk.NET_DVR_CaptureJPEGPicture(id,chanLong,jpeg, fileNameString);
		if (is) {
		System.out.println("抓取成功,返回长度：" + a.getValue());
		} else {
		System.out.println("抓取失败："+sdk.NET_DVR_GetLastError());
		}

		sdk.NET_DVR_Logout(id);
		sdk.NET_DVR_Cleanup();
		
		
		return fileNameString;
		
	}
	
	public static void getDVRPicToM() {
		NativeLong chanLong = new NativeLong(1);


		HCNetSDK sdk = HCNetSDK.INSTANCE;
		if(!sdk.NET_DVR_Init()) {
			System.out.println("SDK初始化失败");
			return;
		}
		
		//注册设备
				HCNetSDK.NET_DVR_DEVICEINFO_V30 devinfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		NativeLong id = sdk.NET_DVR_Login_V30(ipString , portString,
		username, pwd, devinfo);
		
		if (id.intValue() < 0) {
			System.out.println("设备注册失败"+sdk.NET_DVR_GetLastError());
			return;
			} else {
			System.out.println("id：" + id);
			}
		
		// 返回Boolean值，判断是否获取设备能力
		HCNetSDK.NET_DVR_WORKSTATE_V30 devwork = new HCNetSDK.NET_DVR_WORKSTATE_V30();
		if (!sdk.NET_DVR_GetDVRWorkState_V30(id, devwork)) {
		System.out.println("返回设备状态失败");
		}

		//JPEG图像信息结构体
		HCNetSDK.NET_DVR_JPEGPARA jpeg = new HCNetSDK.NET_DVR_JPEGPARA();
		jpeg.wPicSize = 5;// 设置图片的分辨率
		jpeg.wPicQuality = 2;// 设置图片质量

		//设置图片大小
		IntByReference a = new IntByReference(1000);
		
		//创建图片目录
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		int random = (int)(Math.random()*1000);
		String fileNameString = "C:\\Users\\admin\\Desktop\\qrcode\\imag\\" + ".jpg";

		//设置字节缓存
		ByteBuffer jpegBuffer = ByteBuffer.allocate(1024 * 1024);

		//抓图到文件
		//boolean is = sdk.NET_DVR_CaptureJPEGPicture(id,chanLong,jpeg, fileNameString);
		//char buf = char[1024];
		String sJpegPicBuffer = "";
		int dwPicSize = 704*480;
		boolean is = sdk.NET_DVR_CaptureJPEGPicture_NEW(id, chanLong, jpeg, sJpegPicBuffer, dwPicSize,  a);
		if (is) {
		System.out.println("抓取成功,返回长度：" + a.getValue());
		} else {
		System.out.println("抓取失败："+sdk.NET_DVR_GetLastError());
		}

		sdk.NET_DVR_Logout(id);
		sdk.NET_DVR_Cleanup();
		
		
		
		
	}
	
}
