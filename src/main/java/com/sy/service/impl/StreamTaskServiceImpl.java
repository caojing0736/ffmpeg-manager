package com.sy.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.sy.core.Taskcontainer;
import com.sy.entity.pojo.CamaraInfo;
import com.sy.core.excpetion.BusException;
import com.sy.service.IStreamTaskService;
import com.sy.util.FFmpegUtil;
import com.sy.util.RtspUrlUtil;

@Service
public class StreamTaskServiceImpl implements IStreamTaskService{

	@Value("${properties.rtmpPullUrl}")
	private String rtmpPullUrl;
	
	
	
	@Override
	public String startTaskByCamaraInfo(CamaraInfo camaraInfo) throws Exception {

		String taskId = camaraInfo.getVarTaskID();
		if(Taskcontainer.contansTask(taskId)) return taskId;
		
		String rtspUrl = RtspUrlUtil.camaraInfo2Url(camaraInfo);
		
		
		//组装参数
		Map<String, String> map = Maps.newHashMap();
		map.put("appName", taskId);//进程名
        map.put("input",rtspUrl);//拉流rtsp url
        map.put("output",rtmpPullUrl);//推流rtmp url
        map.put("codec", camaraInfo.getVcodec());//编码
        map.put("fmt", camaraInfo.getTsformat());//转换格式
        map.put("fps", camaraInfo.getFrameSpaceString());//帧间隔
        map.put("twoPart", camaraInfo.getTwoPart());//0：元码流，1：自定义；2：多输出
        map.put("rs", camaraInfo.getResolution());//分辨率
		
        taskId = FFmpegUtil.start(map);
        if(taskId == null) throw new BusException("启动任务失败，任务id：" + taskId);
        
        Taskcontainer.addTaskID(taskId);
		
		return taskId;
	}
	
	@Override
	public boolean stopTaskByTaskID(String taskID) {
		
		Taskcontainer.removeTaskID(taskID);
		
		return FFmpegUtil.stop(taskID);
		
	}

}
