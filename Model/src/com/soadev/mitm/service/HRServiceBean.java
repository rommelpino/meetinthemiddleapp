package com.soadev.mitm.service;

import com.soadev.mitm.domain.Job;

import commonj.sdo.DataObject;

import commonj.sdo.helper.XSDHelper;

import javax.ejb.EJB;

import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.JAXBException;

import oracle.webservices.annotations.PortableWebService;

import org.eclipse.persistence.sdo.helper.jaxb.JAXBHelperContext;

@Stateless(name = "HRService", mappedName = "Model-HRService")
@PortableWebService(serviceName = "HRServiceBeanWS",
                    targetNamespace = "http://mitm.soadev.com/service/HRServiceBeanWS",
                    portName = "HRServiceBeanWSSoapHttpPort",
                    endpointInterface =
                    "com.soadev.mitm.service.HRService")
public class HRServiceBean implements HRService{
    @PersistenceContext(unitName="Model")
    private EntityManager em;
    @EJB
    private SessionEJBLocal sessionEJBBean;
    
    public DataObject findJobSDOById(String jobId){
        Job job = sessionEJBBean.findJobById(jobId);
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Job.class);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        JAXBHelperContext jaxbHelperContext =
            new JAXBHelperContext(jaxbContext);
        XSDHelper xsdHelper = jaxbHelperContext.getXSDHelper();
        ClassLoader loader =
            Thread.currentThread().getContextClassLoader();
        xsdHelper.define(loader.getResourceAsStream("xsd/JobSDO.xsd"),
                         "xsd/");
        DataObject sdo = jaxbHelperContext.wrap(job);
        return sdo;
    }

}
