package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.FriendHref;
import com.qicai.dto.FriendHrefDTO;

public interface FriendHrefDao {
	void save(@Param(value="href") FriendHref href);//��
	void update(@Param(value="href")FriendHref href);//��
	void delete(@Param(value="href")FriendHref href);//��
	List<FriendHrefDTO> getListByParam(@Param(value="href")FriendHref href);//��ѯ����
	FriendHrefDTO getByParam(@Param(value="href")FriendHref href);//��ѯ����
}
