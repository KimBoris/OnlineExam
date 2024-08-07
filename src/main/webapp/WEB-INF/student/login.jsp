<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>학생 여러분 반갑습니다.</h1>
<form action="/student/login" method="post">
    <div>
        아이디 <input type="text" name="s_id">
        비밀번호 <input type="password" name="s_pw">
        <button>Login</button>
    </div>
</form>
</body>
</html>
