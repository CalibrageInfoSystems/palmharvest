package in.calibrage.palmharvest.Model;

public class RequestCompleteModel {
    private String RequestCode;
    private String StartDate;
    private String CreatedDate;
    private String UpdatedDate;
    private String JobDoneDate;
    private int StatusTypeID;
    private String YearOfplanting;
    private String NetWeight;
    private String PalmArea;
    private String TressCount;
    private String Farmername;
    private String FormerMobileNumber;
    private String FormerContactNumber;
    private String Landmark;
    private String lattitude;
    private String Longitude;
    private String ServiceTypes;
    private String PlotVilalge;
    private String Servicesname;
    private  Double Exp_netweight;
    private  String exp_bunches;
    private  String inter_treescount;

    public String getServicesname() {
        return Servicesname;
    }

    public void setServicesname(String servicesname) {
        Servicesname = servicesname;
    }

    public String getPlotVilalge() {
        return PlotVilalge;
    }

    public void setPlotVilalge(String plotVilalge) {
        PlotVilalge = plotVilalge;
    }

    public String getRequestCode() {
        return RequestCode;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public String getJobDoneDate() {
        return JobDoneDate;
    }

    public int getStatusTypeID() {
        return StatusTypeID;
    }

    public String getYearOfplanting() {
        return YearOfplanting;
    }

    public String getNetWeight() {
        return NetWeight;
    }

    public String getPalmArea() {
        return PalmArea;
    }

    public String getTressCount() {
        return TressCount;
    }

    public String getFarmername() {
        return Farmername;
    }

    public String getFormerMobileNumber() {
        return FormerMobileNumber;
    }

    public String getFormerContactNumber() {
        return FormerContactNumber;
    }

    public String getLandmark() {
        return Landmark;
    }

    public String getLattitude() {
        return lattitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getServiceTypes() {
        return ServiceTypes;
    }


    public void setRequestCode(String requestCode) {
        RequestCode = requestCode;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public void setJobDoneDate(String jobDoneDate) {
        JobDoneDate = jobDoneDate;
    }

    public void setStatusTypeID(int statusTypeID) {
        StatusTypeID = statusTypeID;
    }

    public void setYearOfplanting(String yearOfplanting) {
        YearOfplanting = yearOfplanting;
    }

    public void setNetWeight(String netWeight) {
        NetWeight = netWeight;
    }

    public void setPalmArea(String palmArea) {
        PalmArea = palmArea;
    }

    public void setTressCount(String tressCount) {
        TressCount = tressCount;
    }

    public void setFarmername(String farmername) {
        Farmername = farmername;
    }

    public void setFormerMobileNumber(String formerMobileNumber) {
        FormerMobileNumber = formerMobileNumber;
    }

    public void setFormerContactNumber(String formerContactNumber) {
        FormerContactNumber = formerContactNumber;
    }

    public void setLandmark(String landmark) {
        Landmark = landmark;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public void setServiceTypes(String serviceTypes) {
        ServiceTypes = serviceTypes;
    }

    public RequestCompleteModel(String requestCode, String startDate, String createdDate, String updatedDate, String jobDoneDate, int statusTypeID, String yearOfplanting, String netWeight, String palmArea, String tressCount, String farmername, String formerMobileNumber, String formerContactNumber, String landmark,
                                String lattitude, String longitude, String serviceTypes, String PlotVilalge,String Servicesname, Double Exp_netweight,String Exp_bunches,String treesCountWithIntercrop) {
        this.RequestCode = requestCode;
        this.StartDate = startDate;
        this.CreatedDate = createdDate;
        this.UpdatedDate = updatedDate;
        this.JobDoneDate = jobDoneDate;
        this.StatusTypeID = statusTypeID;
        this.YearOfplanting = yearOfplanting;
        this.NetWeight = netWeight;
        this.PalmArea = palmArea;
        this.TressCount = tressCount;
        this.Farmername = farmername;
        this.FormerMobileNumber = formerMobileNumber;
        this.FormerContactNumber = formerContactNumber;
        this.Landmark = landmark;
        this.lattitude = lattitude;
        this.Longitude = longitude;
        this.ServiceTypes = serviceTypes;
        this.PlotVilalge = PlotVilalge;
        this.Servicesname = Servicesname;
        this.Exp_netweight=Exp_netweight;
        this.exp_bunches =Exp_bunches;
        this.inter_treescount =treesCountWithIntercrop;
    }

    public Double getExp_netweight() {
        return Exp_netweight;
    }

    public void setExp_netweight(Double exp_netweight) {
        Exp_netweight = exp_netweight;
    }

    public String getEXp_bunches() {
        return exp_bunches;
    }

    public void setEXp_bunches(String EXp_bunches) {
        this.exp_bunches = EXp_bunches;
    }

    public String getInter_treescount() {
        return inter_treescount;
    }

    public void setInter_treescount(String inter_treescount) {
        this.inter_treescount = inter_treescount;
    }
}
