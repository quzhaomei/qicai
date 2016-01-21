<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/adtag" prefix="a"%>
 <c:forEach items="${newPage.param }" var="news">
              	 <li> 
                  <div class="img leftimg">
                    <a href="mobileDetail.html?articalId=${news.articalId }">
	                           <div class="artical_img">
											<img o-width="${news.scanImgWidth }" o-height="${news.scanImgHeight }"
												src="${news.scanImgPath }" />
									</div>
									</a>
                  </div>
                  <div class="contents rightcon">
                      <h2>
                        <a href="mobileDetail.html?articalId=${news.articalId }">
                            <a:trim length="20" content="${news.title }"></a:trim>
                            </a>
                      </h2>
                      <div class="mis bottomfix">
							<fmt:formatDate value="${news.createDate }"
												pattern="yyyy/MM/dd" />
                      </div>
                  </div>
                </li> 
              
              </c:forEach>