<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="divmenu">
		<a href="${pageContext.request.contextPath}/showProductByPage?category=literature">文学</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=life">生活</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=computers">计算机</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=foreign languages">外语</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=manage">经管</a>
		<a href="${pageContext.request.contextPath}/showProductByPage?category=encouragement">励志</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=social sciences">社科</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=academic">学术</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=children">少儿</a>
		<a href="${pageContext.request.contextPath}/showProductByPage?category=art">艺术</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=original">原版</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=science">科技</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=exam">考试</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=home life">生活百科</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage" style="color:#FFFF00">全部商品目录</a>		
</div>
<div id="divsearch">
<form action="${pageContext.request.contextPath}/MenuSearchServlet" id="searchform">
	<table width="100%" border="0" cellspacing="0">
		<tr>
			<td style="text-align:right; padding-right:220px">
				Search 
				<input type="text" name="textfield" class="inputtable" id="textfield" value="please input bname"
				onmouseover="this.focus();"
				onclick="my_click(this, 'textfield');"
				onBlur="my_blur(this, 'textfield');"/> 
				
					<%-- <img src="${pageContext.request.contextPath}/client/images/serchbutton.gif" border="0" style="margin-bottom:-4px" onclick="search()"/>  --%>
				<input type="submit" value="搜索" >
			</td>
		</tr>
	</table>
</form>
</div>