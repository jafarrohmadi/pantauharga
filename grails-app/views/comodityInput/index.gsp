<%@ page import="com.pantau.core.ComodityInput" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName"
           value="${message(code: 'comodityInput.label', default: 'ComodityInput')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<!-- Page Heading -->
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><i class="fa fa-dashboard"></i> <a class="home"
                                                   href="${createLink(uri: '/')}"><g:message
                        code="default.home.label"/></a></li>
            <li class="active"><i class="fa fa-plus"></i> <g:link
                    class="create" action="create">
                <g:message code="default.new.label" args="[entityName]"/>
            </g:link></li>
        </ol>
    </div>
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <g:if test="${flash.message}">
            <div class="alert alert-info" role="status">${flash.message}</div>
        </g:if>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <i class="fa fa-list"></i>
                    <g:message code="default.list.label" args="[entityName]"/>
                </h3>
            </div>

            <table class="table table-striped table-bordered">
                <thead>
                <tr>

                    <th><g:message code="comodityInput.user.label" default="User"/></th>

                    <th><g:message code="comodityInput.type.label" default="Type"/></th>

                    <th><g:message code="comodityInput.region.label" default="Region"/></th>

                    <th><g:message code="comodityInput.comodityName.label" default="Comodity Name"/></th>

                    <g:sortableColumn property="price"
                                      title="${message(code: 'comodityInput.price.label', default: 'Price')}"/>


                    <g:sortableColumn property="dateCreated"
                                      title="${message(code: 'comodityInput.lastUpdated.label', default: 'Last Updated')}"/>

                </tr>
                </thead>
                <tbody>
                <g:each in="${comodityInputInstanceList}" status="i"
                        var="comodityInputInstance">
                    <tr>

                        <td>${fieldValue(bean: comodityInputInstance, field: "user")}</td>

                        <td>${fieldValue(bean: comodityInputInstance, field: "typeString")}</td>

                        <td>${fieldValue(bean: comodityInputInstance, field: "region")}</td>

                        <td>${fieldValue(bean: comodityInputInstance, field: "comodityName")}</td>

                        <td><g:link action="show"
                                    id="${comodityInputInstance.id}">${fieldValue(bean: comodityInputInstance, field: "price")}</g:link></td>

                        <td><g:formatDate date="${comodityInputInstance.lastUpdated}"/></td>

                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="clearfix"></div>
        <g:paginate total="${comodityInputInstanceCount ?: 0}" class="pull-right"/>
    </div>
</div>

<div class="row">
    <div class="col-lg-12 col-md-12 col-xs-12">
        <a class="btn btn-success" href="${g.createLink(controller: 'comodityInput', action: 'download.csv')}">
            <i class="fa fa-download"></i>
            Download CSV
        </a>
    </div>
</div>

<br/>

</body>
</html>
