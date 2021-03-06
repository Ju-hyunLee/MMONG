<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
#view-menu{
	text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//권한변경 확인 팝업
	 $("#adminAuthorityBtn").on("click",function(){
		if(confirm("권한을 변경하시겠습니까?")){
		}else{
			return false;
		}
	 });
});
window.onload=function(){
	$("#total_div").css("min-height",(document.body.scrollHeight-38.4)+"px");
}
</script>

<div id="total_div">
	<section class="wrapper site-min-height">
		<h3>
			검색한 관리자 정보조회
		</h3>


<sec:authorize access="hasRole('ROLE_0')">
	<div class="btn-group btn-group-justified" style="margin-top:50px; margin-bottom:30px;">
		<div class="btn-group">
				<button class="btn btn-theme" type="button" onclick="location.href='/MMONG/admin/register_form.do' ">관리자 등록</button>&nbsp;
		</div>
		<div class="btn-group">	
				<button class="btn btn-theme" type="button" onclick="location.href='/MMONG/admin/search_admin_form.do' ">관리자 찾기</button>&nbsp;
		</div>
		<div class="btn-group">	
				<button class="btn btn-theme" type="button" onclick="location.href='/MMONG/admin/search_member_form.do' ">회원 찾기</button>&nbsp;
		</div>
	</div>
</sec:authorize>



<div style="height:auto; min-width: 350px; text-align:center;">
		<div>
			<h5>관리자정보</h5>
		</div>
		<p><img src="/MMONG/up_image/profileAdmin.PNG" class="img-circle" width="100"></p> 
		<p><b>ID</b> : ${requestScope.administrator.adminId }</p>
		
				<p><b>이름</b> : ${requestScope.administrator.adminName}</p>
				<p><b>전화번호</b> : ${requestScope.administrator.adminPhone }</p>
				<p><b>이메일</b> : ${requestScope.administrator.adminEmail }</p>
				<p><b>권한</b> :  ${requestScope.administrator.user.userAuthority }</p>
</div>

<div id="view-menu">
	<form action="/MMONG/admin/changeAdminAuthority.do" method="post">
			<input type="hidden" name="adminId" value="${requestScope.administrator.adminId }">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input class="btn btn-default btn-sm" type="submit"  id="adminAuthorityBtn" value="활동 정지/재개 변경">
			</form>
</div>
</section>
</div>