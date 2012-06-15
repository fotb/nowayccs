/**
 * MessageManager.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccs.services.client;

public interface MessageManager extends java.rmi.Remote {
    public java.lang.String smsSend(java.lang.String tele_num, java.lang.String sms_content) throws java.rmi.RemoteException;
    public java.lang.String selectAllRecv() throws java.rmi.RemoteException;
    public void updateReadedRecv(java.lang.String id) throws java.rmi.RemoteException;
}
