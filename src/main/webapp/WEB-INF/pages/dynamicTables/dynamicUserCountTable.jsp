<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<display:table name="userCountTable" cellspacing="0" requestURI="./getCurrentUserCount" id="userCountTable" sort="external" partialList="true" size="${size}" pagesize="${gridSize}" export="false">
	<display:column property="countId" title="User No"/>
     <display:column  sortable="false" headerClass="text-center sortable sorted order1" title="Action" media="html" class="action">
		<div class="text-center">
			<button onclick="getUserDetailsBySessionId(${userCountTable.sid}, ${userCountTable.countId})" class="edit-btn btn btn-primary"><span class="fa fa-edit"> </span>Get Details</button>
		</div>
	</display:column>
	<display:setProperty name="basic.empty.showtable" value="true" />
	<display:setProperty name="basic.msg.empty_list" value="" />
</display:table>