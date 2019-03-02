package com.ccs.services.client;

public class UnifiedDataDockingWebServiceProxy implements com.ccs.services.client.UnifiedDataDockingWebService {
  private String _endpoint = null;
  private com.ccs.services.client.UnifiedDataDockingWebService unifiedDataDockingWebService = null;
  
  public UnifiedDataDockingWebServiceProxy() {
    _initUnifiedDataDockingWebServiceProxy();
  }
  
  public UnifiedDataDockingWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initUnifiedDataDockingWebServiceProxy();
  }
  
  private void _initUnifiedDataDockingWebServiceProxy() {
    try {
      unifiedDataDockingWebService = (new com.ccs.services.client.UnifiedDataDockingWebServiceImplServiceLocator()).getUnifiedDataDockingWebServiceImplPort();
      if (unifiedDataDockingWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)unifiedDataDockingWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)unifiedDataDockingWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (unifiedDataDockingWebService != null)
      ((javax.xml.rpc.Stub)unifiedDataDockingWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ccs.services.client.UnifiedDataDockingWebService getUnifiedDataDockingWebService() {
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService;
  }
  
  public java.lang.String addHospital(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addHospital(xml);
  }
  
  public java.lang.String addFloatingPopulation(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addFloatingPopulation(xml);
  }
  
  public java.lang.String addIssue(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addIssue(xml);
  }
  
  public java.lang.String addCommonComplexPlace(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addCommonComplexPlace(xml);
  }
  
  public java.lang.String addSchool(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addSchool(xml);
  }
  
  public java.lang.String addHouseholdStaff(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addHouseholdStaff(xml);
  }
  
  public java.lang.String commentIssue(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.commentIssue(xml);
  }
  
  public java.lang.String addActualHouse(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addActualHouse(xml);
  }
  
  public java.lang.String addPositiveInfo(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addPositiveInfo(xml);
  }
  
  public java.lang.String addNewSocietyOrganizations(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addNewSocietyOrganizations(xml);
  }
  
  public java.lang.String addFileWorkRecord(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addFileWorkRecord(xml);
  }
  
  public java.lang.String submitIssue(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.submitIssue(xml);
  }
  
  public java.lang.String addMentalPatient(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addMentalPatient(xml);
  }
  
  public java.lang.String addFireSafetyEnterprise(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addFireSafetyEnterprise(xml);
  }
  
  public java.lang.String addDangerousGoodsPractitioner(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addDangerousGoodsPractitioner(xml);
  }
  
  public java.lang.String updateIssue(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.updateIssue(xml);
  }
  
  public java.lang.String addSpecialTrade(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addSpecialTrade(xml);
  }
  
  public java.lang.String addPyramidSchemes(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addPyramidSchemes(xml);
  }
  
  public java.lang.String addRentalHouse(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addRentalHouse(xml);
  }
  
  public java.lang.String addOverseaPersonnel(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addOverseaPersonnel(xml);
  }
  
  public java.lang.String addDownEnterprise(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addDownEnterprise(xml);
  }
  
  public java.lang.String addUnsettledPopulation(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addUnsettledPopulation(xml);
  }
  
  public java.lang.String addMeetingWorkRecord(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addMeetingWorkRecord(xml);
  }
  
  public java.lang.String addPublicPlace(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addPublicPlace(xml);
  }
  
  public java.lang.String addSendDelivery(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addSendDelivery(xml);
  }
  
  public java.lang.String addSecurityEnterprise(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addSecurityEnterprise(xml);
  }
  
  public java.lang.String findNeedDoIssue(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.findNeedDoIssue(xml);
  }
  
  public java.lang.String addDruggy(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addDruggy(xml);
  }
  
  public java.lang.String addOtherLocale(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addOtherLocale(xml);
  }
  
  public java.lang.String completeIssue(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.completeIssue(xml);
  }
  
  public java.lang.String feedBackIssue(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.feedBackIssue(xml);
  }
  
  public java.lang.String assignIssue(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.assignIssue(xml);
  }
  
  public java.lang.String addSafetyProduction(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addSafetyProduction(xml);
  }
  
  public java.lang.String addUpEnterprise(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addUpEnterprise(xml);
  }
  
  public java.lang.String addOtherAttentionPersonnel(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addOtherAttentionPersonnel(xml);
  }
  
  public java.lang.String addOtherWorkRecord(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addOtherWorkRecord(xml);
  }
  
  public java.lang.String addRectificativePerson(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addRectificativePerson(xml);
  }
  
  public java.lang.String addInternetBar(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addInternetBar(xml);
  }
  
  public java.lang.String addSuperiorVisit(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addSuperiorVisit(xml);
  }
  
  public java.lang.String addActivityWorkRecord(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addActivityWorkRecord(xml);
  }
  
  public java.lang.String addIdleYouth(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addIdleYouth(xml);
  }
  
  public java.lang.String addDangerousChemicalsUnit(java.lang.String xml) throws java.rmi.RemoteException{
    if (unifiedDataDockingWebService == null)
      _initUnifiedDataDockingWebServiceProxy();
    return unifiedDataDockingWebService.addDangerousChemicalsUnit(xml);
  }
  
  
}