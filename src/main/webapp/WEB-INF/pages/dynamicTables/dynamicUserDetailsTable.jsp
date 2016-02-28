<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<display:table name="userDetailsTable" cellspacing="0" requestURI="./getUserDetailsBySessionId" id="userDetailsTable" sort="external" partialList="true" size="${size}" pagesize="${gridSize}" export="false">

     <display:column property="eventName" title="Event"/>
     <display:column property="tagName" title="Tag"/>
     <display:column property="imageName" title="Image"/>
     <display:column property="eventTriggeredTime" title="Triggered Time"/>
     <display:column property="numOfTaps" title="Taps"/>
     <display:column property="scrollTop" title="ScrollTop"/>
     <display:column title="Event(X,Y)">
     	<label>${userDetailsTable.coordinateX} , ${userDetailsTable.coordinateY}</label>
     </display:column>
     <display:column property="timeZone" title="TimeZone"/>
     <display:column property="zoneDateTime" title="ZoneDateTime"/>
	 <display:column property="deviceName" title="Device Name"/>
	 <display:column property="osName" title="OS"/>
	 <display:column title="Device(W,H)">
	 	<label>${userDetailsTable.screenWidth} , ${userDetailsTable.screenHeight}</label>
	 </display:column>
	 <display:column title="View(W,H)">
	 	<label>${userDetailsTable.viewportWidth} , ${userDetailsTable.viewportHeight}</label>
	 </display:column>
	 <display:column property="orientation" title="Orientation"/>
     <display:column property="browserName" title="Browser"/>
     <display:column property="browserVersion" title="Version"/>
     <display:column property="userAgentId" title="ID"/>
     <display:column  title="Proxies">
     	<label>${userDetailsTable.pid.size()}</label>
     	<c:if test="${userDetailsTable.pid.size() > 0}">
     		<button onclick="getLocationDetails(${userDetailsTable.bid})" style="height: 20px;"><span class="fa fa-edit"> </span>More</button>
     	</c:if>
     </display:column>
     
<%-- 	 <display:setProperty name="basic.empty.showtable" value="true" />
	 <display:setProperty name="basic.msg.empty_list" value="" /> --%>
</display:table>

<script>
	$("#numberOfEvents").text(${size});
</script>