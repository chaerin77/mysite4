<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

<!-- 연결 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="user">
					<div id="joinForm">
						<!-- <form action="${pageContext.request.contextPath}/user/join" method="get"> -->
	
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="overcheck">중복체크</button>
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
							</div>
	
							<!-- 이메일 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>
	
							<!-- //나이 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male" > 
								
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female" > 
	
							</div>
	
							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> 
								
								<input type="checkbox" id="chk-agree" value="" name="">
								<label for="chk-agree">서비스 약관에 동의합니다.</label> 
							</div>
							
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>
							
						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->
		
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->

</body>


<!-- 자바 스크립트 -->
<script type="text/javascript">
	
	
	//로딩된 후 요청
	$(window).load(function(){
		
	});
	
	
	//중복체크 버튼이 클릭 될 때
	$("#overcheck").on("click",function(){
		console.log("클릭");
		
		//입력한 id
		var id = $("#input-uid").val();
		console.log(id);
		
		
		var userVo = {
				id: id				
		};
		console.log(userVo);
	
		
		//220201 컨트롤러에 if조건 달고 public UserVo overCheck -> public String overCheck로 받게끔 바꿈 vo가 null일때 문제생김
		$.ajax({
			//요청
			url : "${pageContext.request.contextPath }/api/user/overCheck",
			type : "post",
			//contentType : "application/json",
			data : userVo,
			
			//응답
			dataType : "json",
			success : function(inputIdresult){ 
				//성공시 처리해야될 코드 작성
				console.log(inputIdresult);
				
				if(inputIdresult == "1"){
					//사용 가능한 아이디 입니다 화면에 뜨도록
					console.log("사용 가능한 아이디입니다.");
					
				}else if(inputIdresult == "0"){
					//중복된 아이디 입니다. 화면에 뜨도록 + 입력한 값 지워지게
					
					console.log("중복된 아이디입니다.")
					$("#input-uid").val("");
				}
			
				
			/*1.	3 실패
			//응답
			dataType : "json",
			success : function(inputId){ //여기 조건 설정이 문제인가
				//성공시 처리해야될 코드 작성
				console.log(inputId);
					
				if(inputId == null){
					//사용 가능한 아이디 입니다 화면에 뜨도록
					console.log("사용 가능한 아이디입니다.");
						
				}else{
					//중복된 아이디 입니다. 화면에 뜨도록 + 입력한 값 지워지게
						
					console.log("중복된 아이디입니다.")
					$("#input-uid").val("");
						
				}	*/
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
		/* 1.이 json관련 오류가 나서 다른방법써보기로 했으나 위에거나 이거나 똑같이 오류
		$.ajax({
			//요청
			url : "${pageContext.request.contextPath }/api/user/overCheck",
			type : "post",
			//contentType : "application/json",
			data : userVo,
			
			//응답
			dataType : "json",
			success : function(inputId){
				//성공시 처리해야될 코드 작성
				message(inputId);
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
		//중복체크 메시지
		function message(id){
			
			if(inputId == null){
				//사용 가능한 아이디 입니다 화면에 뜨도록
				console.log("사용 가능한 아이디입니다.");
					
			}else{
				//중복된 아이디 입니다. 화면에 뜨도록 + 입력한 값 지워지게
				
				console.log("중복된 아이디입니다.")
				$("#input-uid").val("");
			}
		}*/
	
	
	
	});




</script>


</html>