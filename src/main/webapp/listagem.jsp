<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Listagem de todos os alunos</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Matrícula</th>
                <th>Nome</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${alunos}" var="aluno">
                <tr>
                    <td><c:out value="${aluno.matricula}" /></td>
                    <td><c:out value="${aluno.nome}" /></td>
                    
                    <td><a href="AlunoCtrl?action=editar&matricula=<c:out value="${aluno.matricula}"/>">Update</a></td>
                    <td><a href="AlunoCtrl?action=deletar&matricula=<c:out value="${aluno.matricula}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="AlunoCtrl?action=incluir">Adicionar Aluno</a></p>
</body>
</html>