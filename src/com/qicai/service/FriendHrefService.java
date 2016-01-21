package com.qicai.service;

import java.util.List;

import com.qicai.bean.FriendHref;
import com.qicai.dto.FriendHrefDTO;

public interface FriendHrefService extends BaseService<FriendHref, FriendHrefDTO>{
	List<FriendHrefDTO> findAll();
	List<FriendHrefDTO> findListByParam(FriendHref bean);
	FriendHrefDTO findByParam(FriendHref bean);
}
