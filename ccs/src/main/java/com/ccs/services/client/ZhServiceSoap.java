/**
 * ZhServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccs.services.client;

public interface ZhServiceSoap extends java.rmi.Remote {
    public java.lang.String AJ_Upload(java.lang.String user, java.lang.String pw, java.lang.String pXml) throws java.rmi.RemoteException;
    public java.lang.String seat_Upload(java.lang.String user, java.lang.String pw, java.lang.String pXml) throws java.rmi.RemoteException;
    public java.lang.String calltel_Upload(java.lang.String user, java.lang.String pw, java.lang.String pXml) throws java.rmi.RemoteException;
    public java.lang.String ajfjtype_Upload(java.lang.String user, java.lang.String pw, java.lang.String tranid, java.lang.String fjType, byte[] fjStream) throws java.rmi.RemoteException;
    public java.lang.String login(java.lang.String user, java.lang.String pw) throws java.rmi.RemoteException;
}
