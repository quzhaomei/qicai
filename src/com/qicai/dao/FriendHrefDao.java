package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.FriendHref;
import com.qicai.dto.FriendHrefDTO;

public interface FriendHrefDao {
	void save(@Param(value="href") FriendHref href);//增
	void update(@Param(value="href")FriendHref href);//改
	void delete(@Param(value="href")FriendHref href);//改
	List<FriendHrefDTO> getListByParam(@Param(value="href")FriendHref href);//查询数组
	FriendHrefDTO getByParam(@Param(value="href")FriendHref href);//查询单个
}
