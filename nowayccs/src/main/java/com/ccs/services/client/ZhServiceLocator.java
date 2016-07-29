/**
 * ZhServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccs.services.client;

public class ZhServiceLocator extends org.apache.axis.client.Service implements com.ccs.services.client.ZhService {

    public ZhServiceLocator() {
    }


    public ZhServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZhServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZhServiceSoap
    private java.lang.String ZhServiceSoap_address = "http://122.225.204.210:8808/ZhService.asmx";

    public java.lang.String getZhServiceSoapAddress() {
        return ZhServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ZhServiceSoapWSDDServiceName = "ZhServiceSoap";

    public java.lang.String getZhServiceSoapWSDDServiceName() {
        return ZhServiceSoapWSDDServiceName;
    }

    public void setZhServiceSoapWSDDServiceName(java.lang.String name) {
        ZhServiceSoapWSDDServiceName = name;
    }

    public com.ccs.services.client.ZhServiceSoap getZhServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZhServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZhServiceSoap(endpoint);
    }

    public com.ccs.services.client.ZhServiceSoap getZhServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ccs.services.client.ZhServiceSoapStub _stub = new com.ccs.services.client.ZhServiceSoapStub(portAddress, this);
            _stub.setPortName(getZhServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZhServiceSoapEndpointAddress(java.lang.String address) {
        ZhServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ccs.services.client.ZhServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ccs.services.client.ZhServiceSoapStub _stub = new com.ccs.services.client.ZhServiceSoapStub(new java.net.URL(ZhServiceSoap_address), this);
                _stub.setPortName(getZhServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ZhServiceSoap".equals(inputPortName)) {
            return getZhServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ZhService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ZhServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ZhServiceSoap".equals(portName)) {
            setZhServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
