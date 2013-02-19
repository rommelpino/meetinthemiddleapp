package com.soadev.mitm.service;

import commonj.sdo.DataObject;

import javax.ejb.Remote;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import oracle.webservices.annotations.PortableWebService;
import oracle.webservices.annotations.SDODatabinding;

@Remote
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED,
             style = SOAPBinding.Style.DOCUMENT)
@PortableWebService(targetNamespace = "http://mitm.soadev.com/service/HRServiceBeanWS",
                    name = "GLCOAManagerBeanWS",
                    wsdlLocation = "wsdl/HRServiceBeanWS.wsdl")
@SDODatabinding(schemaLocation =
                "xsd/HRServiceBeanWS.xsd")
public interface HRService {
    @WebMethod(action = "http://mitm.soadev.com/service/HRServiceBeanWS/findJobSDOById",
               operationName = "findJobSDOById")
    @RequestWrapper(targetNamespace = "http://mitm.soadev.com/service/HRServiceBeanWS",
                    localName = "findJobSDOById")
    @ResponseWrapper(targetNamespace = "http://mitm.soadev.com/service/HRServiceBeanWS",
                     localName = "findJobSDOByIdResponse")
    @WebResult(name = "result")
    DataObject findJobSDOById(@WebParam(mode = WebParam.Mode.IN, name = "jobId") String jobId);
}
