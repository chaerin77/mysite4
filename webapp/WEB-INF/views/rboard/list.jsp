<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>댓글게시판</h2>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>댓글게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">댓글게시판</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
				
					<form action="${pageContext.request.contextPath}/rboard/addList" method="get">
						
						<table id="guestAdd">
							<tbody>
								<tr>
									<td>댓글 입력</td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="title" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						<input type="hidden" name="user_no" value="${authUser.no}">
						<!-- //guestWrite -->
						
					</form>	
					
					<c:forEach items="${rboardList}" var="rbList">
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>${rbList.no}</td>
							<td>${rbList.name}</td>
							<td>${rbList.user_no}</td>
							<td><a href="${pageContext.request.contextPath}/rboard/writeFormA?groupno=${rbList.group_no}">[답글쓰기]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">${rbList.title}</td>
						</tr>
					</table>
					</c:forEach>
					<!-- //guestRead -->
					
				</div>
				<!-- //guestbook -->	
				
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	
	</div>
	<!-- //wrap -->

</body>

</html>