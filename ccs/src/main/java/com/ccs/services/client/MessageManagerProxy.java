package com.ccs.services.client;

public class MessageManagerProxy implements com.ccs.services.client.MessageManager {
  private String _endpoint = null;
  private com.ccs.services.client.MessageManager messageManager = null;
  
  public MessageManagerProxy() {
    _initMessageManagerProxy();
  }
  
  public MessageManagerProxy(String endpoint) {
    _endpoint = endpoint;
    _initMessageManagerProxy();
  }
  
  private void _initMessageManagerProxy() {
    try {
      messageManager = (new com.ccs.services.client.MessageManagerServiceLocator()).getMessageService();
      if (messageManager != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)messageManager)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)messageManager)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (messageManager != null)
      ((javax.xml.rpc.Stub)messageManager)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ccs.services.client.MessageManager getMessageManager() {
    if (messageManager == null)
      _initMessageManagerProxy();
    return messageManager;
  }
  
  public java.lang.String smsSend(java.lang.String tele_num, java.lang.String sms_content) throws java.rmi.RemoteException{
    if (messageManager == null)
      _initMessageManagerProxy();
    return messageManager.smsSend(tele_num, sms_content);
  }
  
  public java.lang.String selectAllRecv() throws java.rmi.RemoteException{
    if (messageManager == null)
      _initMessageManagerProxy();
    return messageManager.selectAllRecv();
  }
  
  public void updateReadedRecv(java.lang.String id) throws java.rmi.RemoteException{
    if (messageManager == null)
      _initMessageManagerProxy();
    messageManager.updateReadedRecv(id);
  }
  
  
}