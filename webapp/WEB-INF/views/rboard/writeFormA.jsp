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
					<form action="${pageContext.request.contextPath}/rboard/write" method="get">
						<table id="guestAdd">
							<tbody>
								<tr>
									<td colspan="5"><textarea name="content" cols="200" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td><a href="${pageContext.request.contextPath}/rboard/list">[취소]</a></td>
									<td colspan="4" class="text-right"><button type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						
						<!-- //guestWrite -->
					</form>	
					
					
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