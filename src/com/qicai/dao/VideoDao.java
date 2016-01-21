package com.qicai.dao;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.Video;
import com.qicai.dto.VideoDTO;

/**
 *
 * @author Administrator
 *
 */
public interface VideoDao {
	void save(@Param("video")Video video);//增
	void update(@Param("video")Video video);//改
	VideoDTO getByParam(@Param("video")Video video);//查询单个
}
