package com.ccs.services.client;

public class ZhServiceSoapProxy implements com.ccs.services.client.ZhServiceSoap {
  private String _endpoint = null;
  private com.ccs.services.client.ZhServiceSoap zhServiceSoap = null;
  
  public ZhServiceSoapProxy() {
    _initZhServiceSoapProxy();
  }
  
  public ZhServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initZhServiceSoapProxy();
  }
  
  private void _initZhServiceSoapProxy() {
    try {
      zhServiceSoap = (new com.ccs.services.client.ZhServiceLocator()).getZhServiceSoap();
      if (zhServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zhServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zhServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zhServiceSoap != null)
      ((javax.xml.rpc.Stub)zhServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ccs.services.client.ZhServiceSoap getZhServiceSoap() {
    if (zhServiceSoap == null)
      _initZhServiceSoapProxy();
    return zhServiceSoap;
  }
  
  public java.lang.String AJ_Upload(java.lang.String user, java.lang.String pw, java.lang.String pXml) throws java.rmi.RemoteException{
    if (zhServiceSoap == null)
      _initZhServiceSoapProxy();
    return zhServiceSoap.AJ_Upload(user, pw, pXml);
  }
  
  public java.lang.String seat_Upload(java.lang.String user, java.lang.String pw, java.lang.String pXml) throws java.rmi.RemoteException{
    if (zhServiceSoap == null)
      _initZhServiceSoapProxy();
    return zhServiceSoap.seat_Upload(user, pw, pXml);
  }
  
  public java.lang.String calltel_Upload(java.lang.String user, java.lang.String pw, java.lang.String pXml) throws java.rmi.RemoteException{
    if (zhServiceSoap == null)
      _initZhServiceSoapProxy();
    return zhServiceSoap.calltel_Upload(user, pw, pXml);
  }
  
  public java.lang.String ajfjtype_Upload(java.lang.String user, java.lang.String pw, java.lang.String tranid, java.lang.String fjType, byte[] fjStream) throws java.rmi.RemoteException{
    if (zhServiceSoap == null)
      _initZhServiceSoapProxy();
    return zhServiceSoap.ajfjtype_Upload(user, pw, tranid, fjType, fjStream);
  }
  
  public java.lang.String login(java.lang.String user, java.lang.String pw) throws java.rmi.RemoteException{
    if (zhServiceSoap == null)
      _initZhServiceSoapProxy();
    return zhServiceSoap.login(user, pw);
  }
  
  
}