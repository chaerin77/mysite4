<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/asideGallery.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
					<c:if test="${!empty authUser}">
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
					</c:if>
			
					<ul id="viewArea">
						<%-- <c:forEach></c:forEach> list..user_no아니면 no갖고와서 이름바꿔야함..유재석--%>
						<!-- 이미지반복영역 -->
						<c:forEach items="${galleryList}" var="gList">
							<li>
								<div class="view" >
									<img class="imgItem" src="${pageContext.request.contextPath }/upload/${gList.saveName}">
									<div class="imgWriter">작성자: <strong>${gList.name}</strong></div>
								</div>
							</li>
						</c:forEach>	
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form action="${pageContext.request.contextPath }/gallery/addList" method="post" enctype="multipart/form-data" >
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="selectFile">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
					<input type=hidden name="user_no" value="${authUser.no}">
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src ="" > <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
				</div>
				<!-- <form method="" action=""> -->
					<div id="detailModalFooter" class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<%-- <c:if test="${authUser.no == galleryList.user_no}"> --%>
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
						<%-- </c:if> --%>
					</div>
				<!-- </form> -->
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">
	//로딩 된 후 요청
	$(window).load(function(){ 
		
	});
	
	//이미지올리기 버튼이 클릭될때
	$("#btnImgUpload").on("click",function(){
		console.log("이미지 올리기 버튼 클릭")
		$("#addModal").modal('show');
		
		//var file = $("[name='selectFile']").val();
	})
	
	
	//이미지를 클릭했을 때
	$("img[class='imgItem']").on("click",function(){
		console.log("이미지 클릭");
		
		var content = $("[name='content']").val();
		console.log(content);
		
		$("#viewModal").modal('show');
		var $this = $(this);
		
		var file = $this.attr("src");
		console.log(file);
	    $("#viewModelImg").val(file);
		
		var content = $this.data("content");
		console.log(content);
		
		
		/*
		$.ajax({
			//요청
			url : "${pageContext.request.contextPath }/gallery/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},
			
			//응답
			dataType : "json",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
		/*
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			
		});*/
	
	
	})
	

</script>




</html>

