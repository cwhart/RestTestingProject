<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/10/2018
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
<div class="col-md-10">
    <h2>Add New DVD</h2>
    <form class="form-horizontal"
          role="form" method="POST"
          action="${pageContext.request.contextPath}/createDvd">
        <div class="form-group">
            <label for="add-title" class="col-md-4 control-label">Title:</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="add-title"
                       name="title" placeholder="Title"/>
            </div>
        </div>
        <div class="form-group">
            <label for="add-director-name" class="col-md-4 control-label">Director Name:</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="add-director-name"
                       name="directorName" placeholder="Director Name"/>
            </div>
        </div>
        <div class="form-group">
            <label for="add-release-year" class="col-md-4 control-label">Release Year:</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="add-release-year"
                       name="releaseYear" placeholder="Release Year"/>
            </div>
        </div>
        <div class="form-group">
            <label for="add-rating" class="col-md-4 control-label">Rating:</label>
            <div class="col-md-8">
                <select class="form-control" id="add-rating" name="rating">
                    <option value="G">G</option>
                    <option value="PG">PG</option>
                    <option value="PG-13">PG-13</option>
                    <option value="R">R</option>
                </select>
                <!-- <input type="text" class="form-control" id="add-rating"
                        name="rating" placeholder="Rating"/> -->
            </div>
        </div>
        <div class="form-group">
            <label for="add-notes" class="col-md-4 control-label">Notes:</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="add-notes"
                       name="notes" placeholder="Notes"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create DVD"/>
                </div>
        </div>
    </form>

</div>
</div>
</body>
</html>
