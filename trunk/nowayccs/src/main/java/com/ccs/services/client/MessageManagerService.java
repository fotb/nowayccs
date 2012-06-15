/**
 * MessageManagerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccs.services.client;

public interface MessageManagerService extends javax.xml.rpc.Service {
    public java.lang.String getMessageServiceAddress();

    public com.ccs.services.client.MessageManager getMessageService() throws javax.xml.rpc.ServiceException;

    public com.ccs.services.client.MessageManager getMessageService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
