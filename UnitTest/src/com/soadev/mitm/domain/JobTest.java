package com.soadev.mitm.domain;


import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XSDHelper;

import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;

import oracle.bpel.services.common.util.XMLUtil;

import org.apache.commons.lang.SerializationUtils;

import org.eclipse.persistence.sdo.helper.jaxb.JAXBHelperContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import org.w3c.dom.Element;


public class JobTest {
    @Test
    public void testMarshallPojoToSdo() {
        System.out.println("test");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Job.class);
            JAXBHelperContext jaxbHelperContext =
                new JAXBHelperContext(jaxbContext);
            XSDHelper xsdHelper = jaxbHelperContext.getXSDHelper();
            ClassLoader loader =
                Thread.currentThread().getContextClassLoader();
            xsdHelper.define(loader.getResourceAsStream("xsd/JobSDO.xsd"),
                             "xsd/");
            Job job = getJobInstance();
            DataObject sdo = jaxbHelperContext.wrap(job);
            System.out.println(sdo);
            Job job2 = (Job)jaxbHelperContext.unwrap(sdo);
            System.out.println("jobId=" + job2.getJobId());
            DocumentBuilder docBuilder =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document document = docBuilder.newDocument();
            XMLDocument xmlDoc =
                jaxbHelperContext.getXMLHelper().createDocument(sdo,
                                                                sdo.getType().getURI(),
                                                                "jobSDO");
            System.out.println("xmlDoc: " + xmlDoc);
            jaxbHelperContext.getXMLHelper().save(xmlDoc,
                                                  new DOMResult(document),
                                                  null);
            Element element = document.getDocumentElement();
            System.out.println(XMLUtil.toString(element));
            assertEquals("Some elements not marshalled to xml", 4,
                         element.getChildNodes().getLength());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getCause().getLocalizedMessage());
        }

    }

    @Test
    public void testSerialization() {
        SerializationUtils.serialize(getJobInstance());
    }

    public static Job getJobInstance() {
        Job job = new Job();
        job.setJobId("SAODEV");
        job.setJobTitle("SOA Developer");
        job.setMaxSalary(10000L);
        job.setMinSalary(5000L);
        return job;
    }
}
