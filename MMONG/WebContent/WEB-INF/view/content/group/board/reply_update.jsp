<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 
<script type="text/javascript" src="/MMONG/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
window.onload=function(){
	$("#total_div").css("min-height", (document.body.scrollHeight-38.4)+"px");
}
</script>

<div id="total_div">
	<section class="wrapper site-min-height">

<%-- =============소모임 상세페이지 소메뉴 : 밑에 두메뉴안에도 이것 포함시키기! ================ --%>
	<div class="btn-group btn-group-justified" style="margin-top:50px; margin-bottom:30px;"">
	  <div class="btn-group">
	    <a href="/MMONG/group/groupDate/allGroupDateList.do"><button type="button" class="btn btn-theme">모임 일정 목록</button></a>
	  </div>
	  <div class="btn-group">
	    <a href="/MMONG/group/board/allBoardList.do"><button type="button" class="btn btn-theme">자유게시판</button></a>
	  </div>
	  <div class="btn-group">
	    <a href="/MMONG/group/mygroup.do"><button type="button" class="btn btn-theme">나의 소모임</button></a>
	  </div>
	</div>			
<%-- =============소모임 상세페이지 소메뉴 끝================ --%>


<hr>
	<input type="button"class="btn btn-default btn-sm" value="글쓰기" onclick="location.href='/MMONG/group/board/board_form.do'">
	<input type="button"class="btn btn-default btn-sm" value="내가 쓴 글" onclick="location.href='/MMONG/group/board/myBoardList.do'">
	<input type="button"class="btn btn-default btn-sm" value="내가 쓴 댓글" onclick="location.href='/MMONG/group/reply/myReplyList.do'">


	<table>
	
		<tr>
			<td>제목</td>
			<td>${requestScope.board.title }</td>
		</tr>
		<tr>
			<td>작성자(닉네임)</td>
			<td>${requestScope.board.memberId }(${requestScope.boardNickname })</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${requestScope.board.content }<br>
			<c:forEach items="${requestScope.nameList }" var="fileName">
				<img src="/MMONG/up_image/${fileName }" width="400px">
				<input type="hidden" name="nameList" value="${fileName }">
			</c:forEach></td>
		</tr>
		<tr>
			<td>작성일자</td>
			<td><fmt:formatDate value="${requestScope.board.boardDate }" pattern="yyyy-MM-dd a hh:mm"/> </td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${requestScope.board.hit }</td>
		</tr>
	</table> 
	
<table>
	<c:forEach items="${requestScope.replyList }" var="reply">
		<c:choose>
			<c:when test="${reply.no==requestScope.replyNo }">
			<tr>
				<td>
				<form action="/MMONG/group/reply/replyUpdate.do" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="hidden" name="replyNo" value="${reply.no }">
					<input type="hidden" name="boardNo" value="${requestScope.board.no }">
					<input type="hidden" name="memberId" value="${reply.memberId }">
					<input type="text" name="content" value="${reply.content }">
					<input type="submit" value="수정완료">
				</form>
				</td>
				<td>${reply.memberId }(${requestScope.replyNickname })</td>
				<td>${reply.replyDate }</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>${reply.content }</td>
					<td>${reply.memberId }(${reqeustScope.replyNickname })</td>
					<td>${reply.replyDate }</td>
				</tr>	
			</c:otherwise>
		</c:choose>
	</c:forEach>
</table>
</section>
</div>