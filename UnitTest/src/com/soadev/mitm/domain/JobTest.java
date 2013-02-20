package com.soadev.mitm.domain;


import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XSDHelper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;

import javax.xml.transform.dom.DOMSource;

import oracle.bpel.services.common.util.XMLUtil;

import org.apache.commons.lang.SerializationUtils;

import org.eclipse.persistence.sdo.helper.jaxb.JAXBHelperContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class JobTest {
    @Test
    public void testMarshallPojoToSdo() {
            String elementName = "varSDO";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Job.class);
            JAXBHelperContext jaxbHelperContext =
                new JAXBHelperContext(jaxbContext);
            XSDHelper xsdHelper = jaxbHelperContext.getXSDHelper();
            ClassLoader loader =
                Thread.currentThread().getContextClassLoader();
            xsdHelper.define(loader.getResourceAsStream("xsd/JobSDO.xsd"),
                             "xsd/");
            Job pojo = getJobInstance();
            DataObject sdoFromPojo = jaxbHelperContext.wrap(pojo);
            DocumentBuilder docBuilder =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document document = docBuilder.newDocument();
            XMLDocument xmlDoc =
                jaxbHelperContext.getXMLHelper().createDocument(sdoFromPojo,
                                                                sdoFromPojo.getType().getURI(),
                                                                elementName);
            jaxbHelperContext.getXMLHelper().save(xmlDoc,
                                                  new DOMResult(document),
                                                  null);
            Element element = document.getDocumentElement();
            System.out.println(XMLUtil.toString(element));
            assertEquals("Some elements not marshalled to xml", 4,
                         element.getChildNodes().getLength());
            XMLDocument xmlDocument = 
                          jaxbHelperContext.getXMLHelper().load(new DOMSource(element), null, null);
            DataObject sdoFromElement = xmlDocument.getRootObject();
            Job pojoFromElement = (Job)jaxbHelperContext.unwrap(sdoFromElement);
            assertEquals(pojo.getJobTitle(), pojoFromElement.getJobTitle());
            
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getCause().getLocalizedMessage());
        }

    }
    
    @Test
    public void testSimpleMarshallPojoToSdo() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Job.class);
            DocumentBuilder docBuilder =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.newDocument();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Job pojo = getJobInstance();
            marshaller.marshal(pojo, document);
            Element element = document.getDocumentElement();
            System.out.println(XMLUtil.toString(element));
            assertEquals("Some elements not marshalled to xml", 4,
                         element.getChildNodes().getLength());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Job pojoFromElement = (Job)unmarshaller.unmarshal(element);
            assertEquals(pojo.getJobTitle(), pojoFromElement.getJobTitle());
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testSerialization() {
        SerializationUtils.serialize(getJobInstance());
    }

    public static Job getJobInstance() {
        Job job = new Job();
        job.setJobId("SOADEV");
        job.setJobTitle("SOA Developer");
        job.setMaxSalary(10000L);
        job.setMinSalary(5000L);
        List<Employee> employeeList = new ArrayList<Employee>();
        Employee employee = new Employee();
        employee.setFirstName("Rommel");
        employeeList.add(employee);
        job.setEmployeeList(employeeList);
        return job;
    }
}
