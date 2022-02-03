<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<!--<form action="${pageContext.request.contextPath}/api/guestbook/write" method="get">-->
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id="btnSubmit2" type="submit">등록</button>
									</td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						
					</form>	
					
					<!--  
					<c:forEach items="${gList}" var="guestList">
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>${guestList.no}</td>
							<td>${guestList.name}</td>
							<td>${guestList.regDate}</td>
							<td><a href="${pageContext.request.contextPath}/guest/deleteForm?no=${guestList.no}">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">${guestList.content}</td>
						</tr>
					</table>
					</c:forEach> -->
					<!-- //guestRead -->
					<!-- 이 자리에 붙어야함 -->
					
					<div id="listArea">
						<!-- 리스트 출력할곳 -->
					</div>
					
					
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	
	</div>
	<!-- //wrap -->



<!-- 삭제 모달창 -->

<div id="delModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">비밀번호 입력 모달창</h4>
      </div>
      <div class="modal-body">
      
      	비밀번호:
      	<input id="modalPassword" type="password" name="password" value=""><br><!-- 삭제 눌렀을때 name으로쓰기엔 이값말고 다른값도 영향가서 id부여함 -->
      	<input id="modalNo" type="text" name="no" value=""><!-- 삭제버튼누르면 내가누른 데이터의 no가뜨게끔 value에 --> 
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- //삭제 모달창 -->

</body>

<!-- javascript 실행되는 시점이 다름 -->
<script type="text/javascript">
	/*로딩되기전에 요청*/
	$(document).ready(function(){ //문서가 준비되면 ()안의 function의 코드(기능) 요청
		
			/*로딩되기 전에 리스트 그리기*/
			fetchList();
			//console.log("리스트 요청"); //이 시점에 리스트 요청해야함
	});	
	
	//저장버튼이 클릭될때(이벤트) - 파라미터 방식 요청
	/*
	$("#btnSubmit").on("click",function(){
			console.log("클릭");
			
			//폼에 데이터를 모아야함
			// .val() 데이터가져오기-입력창에서 입력한 값
			var name = $("#input-uname").val(); //이름
			var password = $("#input-pass").val();
			var content = $("[name='content']").val(); //속성으로 가져오는것 [ ]
			
			//위의 세개 데이터 객체로 만들어서 묶기
			var guestbookVo = {
					name: name, // 필드명: 입력한값(value에서 갖고온값)	
					password: password,
					content: content
			};
			
			//확인
			console.log(guestbookVo);
			
			
			//요청하기
			$.ajax({
				
					url : "${pageContext.request.contextPath }/api/guestbook/write",
					type : "post",
					//contentType : "application/json",
					data : guestbookVo,    //데이터 넣기{name: name, password: password, content: content}
					
					//dataType : "json", //받는 데이터 형태가 json
					success : function(guestbookVo){ //테이터 자바스크립트 형태로 바뀌어서 옴
						
							/*성공시 처리해야될 코드 작성*/
							/*
							console.log(guestbookVo);
							render(guestbookVo,"up");//왜 여기는 큰따옴표 넣는지? --메소드를 그렇게 만들었음
									
							//등록 누른후에 내가 입력한 정보가 창에 그대로 남아있어서 그거 지울것
							$("#input-uname").val("");
							$("#input-pass").val("");
							$("[name='content']").val("");
						
					},
					error : function(XHR, status, error) {
							console.error(status + " : " + error);
					}
			});
			
	}); //괄호 잘 맞추기 */
		
	
	//저장버튼이 클릭될때(이벤트) - json 방식 요청
	$("#btnSubmit2").on("click", function(){
			console.log("json 전송 클릭")
			
			//폼에 데이터를 모아야함
			// .val() 데이터가져오기-입력창에서 입력한 값
			var name = $("#input-uname").val(); //이름
			var password = $("#input-pass").val();
			var content = $("[name='content']").val(); //속성으로 가져오는것 [ ]
			
			//위의 세개 데이터 객체로 만들어서 묶기
			var guestbookVo = {
					name: name, // 필드명: 입력한값(value에서 갖고온값)	
					password: password,
					content: content
			};
			console.log(guestbookVo);
			
		
			$.ajax({
						
				url : "${pageContext.request.contextPath}/api/guestbook/write2",
				type : "post",
				contentType : "application/json",
				data : JSON.stringify(guestbookVo), //새로운 방식 ---자바스크립트 객체를 json형식으로 변경  바디에 서로 해석 가능한 문자열 붙여서 보냄 기존 파라미터방식과 전혀 다른 방식
							
				//dataType : "json", //받는 데이터 형태가 json
				success : function(guestbookVo){
				
						/*성공시 처리해야될 코드 작성*/
				
						console.log(guestbookVo);
						render(guestbookVo,"up");//왜 여기는 큰따옴표 넣는지? --메소드를 그렇게 만들었음
								
						//등록 누른후에 내가 입력한 정보가 창에 그대로 남아있어서 그거 지울것
						$("#input-uname").val("");
						$("#input-pass").val("");
						$("[name='content']").val("");
					
					},
					error : function(XHR, status, error) {
							console.error(status + " : " + error);
					}
			});
	
	});
	
	
	//삭제 팝업 버튼을 눌렀을때  220203
	$("#listArea").on("click", ".btnDelPop", function(){
		//데이터 수집
		var $this = $(this); //자바스크립트변수 = 제이쿼리 좌변의 $this -aaa이렇게써도됨 그냥 변수명
		//내가 방금클릭한 btnDelPop을 알려줌
		
		var no = $this.data("no");//밑의 ****에서 data-__여기에정한 이름 괄호에 넣어서 데이터 꺼낼수있음 이름 항상 소문자로써야함 중요!!
		console.log(no);
		
		//console.log("btnDelPop 클릭"); //**여기까지 했는데 삭제버튼 누르면 이벤트 안잡히는 이유? -->실습 ex15 내용연결 --부모에게 이벤트알려줘야 나중에 새로 생긴 녀석도 이벤트 잡을수있음
		//--> 그래서 이벤트를 btnDelPop에 줄게아니라 저 위에있는 listArea에 줘야함 -->일단 걸렸는데 삭제버튼아닌곳 눌러도 그냥 listArea박스 자체로 인식해서 콘솔찍힘
		//--> ".btnDelPop" 실제로는 얘가 실행해야한다고 써줘야함 (직접 클릭할수있는경우에는 직접 부여해줘야함 지금은 상황이 그게아니라서 이렇게한거)
		//--> 46번사람의 삭제인지 37번사람의 삭제인지 구별하려면? -->지금 클릭한 녀석을 알아야함 ->this 
		
		//var no		
				
		//화면 채우는 회색바탕 만들기
		//초기화
		$("#modalPassword").val("");
		$("#modalNo").val(no); //내가 클릭한 데이터의 no값이 입력폼 안에 들어감
		
		$("#delModal").modal('show');//모달창 열었을때
		
	});
	
	//모달창의 삭제버튼을 클릭했을때
	$("#modalBtnDel").on("click", function(){
		console.log("모달창 삭제버튼 클릭");	
	
		//데이터 수집
		var no = $("#modalNo").val();//**1
		var pw = $("#modalPassword").val();
		
		var delInfoVo = {
			no: no,
			password: pw
		}
		
		console.log(delInfoVo);
		
		
		//ajax요청 no password
		$.ajax({
			//요청
			url : "${pageContext.request.contextPath }/api/guestbook/remove",
			type : "post",
			//contentType : "application/json",
			data : delInfoVo, //데이터 두개니까 vo로 묶어주기 
			
			//응답
			dataType : "json",
			success : function(state){
				console.log(state);
				
				if(state== 'success'){
					/*성공시 처리해야될 코드 작성*/
					
					//  해당테이블html 삭제 --타고타고 부모를찾아가서 지우는방법도있지만 다른방법씀 table class에 no를이용해서 id넣어놓을것임
					$("#t"+no).remove();//**1
					
					//  모달창 닫기 
					$("#delModal").modal('hide');
					
				}else{
					$("#delModal").modal('hide');
					alert("비밀번호를 확인하세요");
				}
				
				//화면에서 변경되는 부분 반영 
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	//리스트 출력
	function fetchList(){ //이런 기능 가진 메소드 만들것 기명함수라서 앞에쓰든뒤에쓰든 상관없음
		
			$.ajax({ // { 객체  키:값 자바스크립트 문법 }
				
					//요청
					url : "${pageContext.request.contextPath }/api/guestbook/addList", //요청할 위치 쓰는곳
					type : "post", //요청방식 get/post
					//contentType : "application/json",
					//data : {name: ”홍길동"},
						
					//응답
					dataType : "json",
					success : function(guestbookList){//자바스크립트 ()안은 내가 정한이름 guestbookList아니어도 결과는 나오지만 같은이름으로 짓는게 안헷갈릴듯
						/*성공시 처리해야될 코드 작성*/
						console.log(guestbookList);
						//console.log(guestbookList[0].name);
					
						//데이터 섞어서 그리기 메소드--그리기(guestbookList);  for문 잘쓰기
						for(var i=0; i<guestbookList.length; i++){
							render(guestbookList[i],"down"); //방명록리스트 그리기시키는애 --**
						}
					
					
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
			});
	}


	//리스트 그리기
	function render(guestbookVo, updown){ //문자열.. --** 데이터 한줄씩 넣는거라 이름vo로 지음
		
		var str = "";
		str += '<table id="t'+guestbookVo.no+'" class="guestRead">'; //id = t40 이런식으로 쓰려구
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>'+guestbookVo.no+'</td>'; //자바스크립트의 변수라서 $ +대괄호 못씀 주석안에다가 저거쓰면 오류나서 이렇게 표현
		str += '		<td>'+guestbookVo.name+'</td>'; //문자열로 표현하는거니까 +써줬음
		str += '		<td>'+guestbookVo.regDate+'</td>';
		str += '		<td><button class="btnDelPop" type="button" data-no="'+guestbookVo.no+'">삭제</button></td>';//**** no자리 이름 소문자로만 써야함!!
						//220203 a링크 -> 버튼으로 바꾸고 id지정해주기 -> 같은id여러개인문제,각각 다르게 지정해준다해도 문제생길듯 -> class로 지정
						//태그에 데이터 숨겨놓기 data-__   ' ' ->문자열 시작
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
		str += '	</tr>';
		str += '</table>';
		
		
		if(updown == 'down'){
			//$("#listArea").html(str); //id=listArea인곳에 html태그로 표현  exam07 
			//--html쓰면 for문돌때마다 바뀐값으로 하나의값만 나오게됨 depend / append 쓰기
			$("#listArea").append(str); //뒤에붙이기
			//.html(str)썼을때 유재석이나왔음 -> 내 db저장된 데이터중 가장 오래된데이터가 마지막에있음
			//정렬을 최신순으로 했기때문에 .html(str)로 실행시키면 가장 마지막값이 정렬의마지막값인 유재석인것
			//최신순 정렬이라 가져온 가장첫번째정보는, 배열로따지면 [0]의 정보는 xx일것임
			//그럼 [1]은 그 뒤에 붙여야하니까 append 사용했음	
			
		}else if(updown == 'up'){
			$("#listArea").prepend(str);
		}else{
			console.log("방향오류");
		}
		
		
		
	};
	
	
	
</script>


</html>