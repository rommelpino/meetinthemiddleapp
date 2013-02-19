<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces/rich" prefix="af"%>
<f:view>
  <af:document id="d1">
    <af:messages id="m1"/>
    <af:form id="f1">
      <af:table value="#{bindings.employeeFindAll.collectionModel}" var="row"
                rows="#{bindings.employeeFindAll.rangeSize}"
                emptyText="#{bindings.employeeFindAll.viewable ? 'No data to display.' : 'Access Denied.'}"
                fetchSize="#{bindings.employeeFindAll.rangeSize}"
                rowBandingInterval="0" id="t1">
        <af:column sortProperty="commissionPct" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.commissionPct.label}"
                   id="c3">
          <af:outputText value="#{row.commissionPct}" id="ot8">
            <af:convertNumber groupingUsed="false"
                              pattern="#{bindings.employeeFindAll.hints.commissionPct.format}"/>
          </af:outputText>
        </af:column>
        <af:column sortProperty="email" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.email.label}"
                   id="c5">
          <af:outputText value="#{row.email}" id="ot1"/>
        </af:column>
        <af:column sortProperty="employeeId" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.employeeId.label}"
                   id="c8">
          <af:outputText value="#{row.employeeId}" id="ot7">
            <af:convertNumber groupingUsed="false"
                              pattern="#{bindings.employeeFindAll.hints.employeeId.format}"/>
          </af:outputText>
        </af:column>
        <af:column sortProperty="firstName" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.firstName.label}"
                   id="c4">
          <af:outputText value="#{row.firstName}" id="ot5"/>
        </af:column>
        <af:column sortProperty="hireDate" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.hireDate.label}"
                   id="c1">
          <af:outputText value="#{row.hireDate}" id="ot9">
            <af:convertDateTime pattern="#{bindings.employeeFindAll.hints.hireDate.format}"/>
          </af:outputText>
        </af:column>
        <af:column sortProperty="lastName" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.lastName.label}"
                   id="c9">
          <af:outputText value="#{row.lastName}" id="ot6"/>
        </af:column>
        <af:column sortProperty="phoneNumber" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.phoneNumber.label}"
                   id="c2">
          <af:outputText value="#{row.phoneNumber}" id="ot2"/>
        </af:column>
        <af:column sortProperty="salary" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.salary.label}"
                   id="c7">
          <af:outputText value="#{row.salary}" id="ot4">
            <af:convertNumber groupingUsed="false"
                              pattern="#{bindings.employeeFindAll.hints.salary.format}"/>
          </af:outputText>
        </af:column>
        <af:column sortProperty="status" sortable="false"
                   headerText="#{bindings.employeeFindAll.hints.status.label}"
                   id="c6">
          <af:outputText value="#{row.status}" id="ot3"/>
        </af:column>
      </af:table>
    </af:form>
  </af:document>
</f:view>