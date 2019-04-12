/**
 * UnifiedDataDockingWebServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccs.services.client;

public class UnifiedDataDockingWebServiceImplServiceLocator extends org.apache.axis.client.Service implements com.ccs.services.client.UnifiedDataDockingWebServiceImplService {

    public UnifiedDataDockingWebServiceImplServiceLocator() {
    }


    public UnifiedDataDockingWebServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UnifiedDataDockingWebServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UnifiedDataDockingWebServiceImplPort
    //private java.lang.String UnifiedDataDockingWebServiceImplPort_address = "http://172.17.229.237:8088/services/unifiedDataDockingWebService";
    private java.lang.String UnifiedDataDockingWebServiceImplPort_address = "http://anhaooray.oicp.net:24582/services/unifiedDataDockingWebService";

    public java.lang.String getUnifiedDataDockingWebServiceImplPortAddress() {
        return UnifiedDataDockingWebServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UnifiedDataDockingWebServiceImplPortWSDDServiceName = "UnifiedDataDockingWebServiceImplPort";

    public java.lang.String getUnifiedDataDockingWebServiceImplPortWSDDServiceName() {
        return UnifiedDataDockingWebServiceImplPortWSDDServiceName;
    }

    public void setUnifiedDataDockingWebServiceImplPortWSDDServiceName(java.lang.String name) {
        UnifiedDataDockingWebServiceImplPortWSDDServiceName = name;
    }

    public com.ccs.services.client.UnifiedDataDockingWebService getUnifiedDataDockingWebServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UnifiedDataDockingWebServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUnifiedDataDockingWebServiceImplPort(endpoint);
    }

    public com.ccs.services.client.UnifiedDataDockingWebService getUnifiedDataDockingWebServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ccs.services.client.UnifiedDataDockingWebServiceImplServiceSoapBindingStub _stub = new com.ccs.services.client.UnifiedDataDockingWebServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getUnifiedDataDockingWebServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUnifiedDataDockingWebServiceImplPortEndpointAddress(java.lang.String address) {
        UnifiedDataDockingWebServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ccs.services.client.UnifiedDataDockingWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ccs.services.client.UnifiedDataDockingWebServiceImplServiceSoapBindingStub _stub = new com.ccs.services.client.UnifiedDataDockingWebServiceImplServiceSoapBindingStub(new java.net.URL(UnifiedDataDockingWebServiceImplPort_address), this);
                _stub.setPortName(getUnifiedDataDockingWebServiceImplPortWSDDServiceName());
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
        if ("UnifiedDataDockingWebServiceImplPort".equals(inputPortName)) {
            return getUnifiedDataDockingWebServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService.tianque.com/", "UnifiedDataDockingWebServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService.tianque.com/", "UnifiedDataDockingWebServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UnifiedDataDockingWebServiceImplPort".equals(portName)) {
            setUnifiedDataDockingWebServiceImplPortEndpointAddress(address);
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
