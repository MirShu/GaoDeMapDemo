package com.example.gaodemapdemo.activity.activity.bean;


/**
 * Created by 舒林
 * on 2016/3/10.
 */
public class OnLineConfigNode  implements Comparable<OnLineConfigNode> {

    private String nodename;
    private String nodeexplain;
    private int fid;
    private int nodeindex;
    private String nodetype;
    private int NODEID;
    //private String alphas;
    private String classPath;
    private String color;
    private String explain;
    private String field;
    private String FileSize;
    private String filterLaye;
    private String form;
    private String icon;
    private int id;
    private boolean istpk;
    private String key;
    private String layertype;
    //private String LayerType;
    private String markerICon;
    private String OfflineMapsUrl;
    private Integer order;
    private String ReturnAttribute;
    public String returnAttribute;
    private String sds;
    private Boolean show;
    private String statisAttribute;
    private String statisticsAttribute;
    private String type;
    private String url;
    private int Version;
    private String whereAttribute;
    private boolean bChecked;
    private float alphas;//不透明度 0-全透明 1-不透明
    private Boolean isshowlayer;//是否在图层控制中显示
    private Boolean issearchproperty;//是否用于属性查询

    public Boolean getShow() {
        return show;
    }



    public OnLineConfigNode(String nodename,String layertype,String url,boolean istpk){
        this.nodename=nodename;
        this.layertype=layertype;
        this.url=url;
        this.istpk=istpk;

    }








    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getNodeexplain() {
        return nodeexplain;
    }

    public void setNodeexplain(String nodeexplain) {
        this.nodeexplain = nodeexplain;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getNodeindex() {
        return nodeindex;
    }

    public void setNodeindex(int nodeindex) {
        this.nodeindex = nodeindex;
    }

    public String getNodetype() {
        return nodetype;
    }

    public void setNodetype(String nodetype) {
        this.nodetype = nodetype;
    }

    public int getNODEID() {
        return NODEID;
    }

    public void setNODEID(int NODEID) {
        this.NODEID = NODEID;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    public String getFilterLaye() {
        return filterLaye;
    }

    public void setFilterLaye(String filterLaye) {
        this.filterLaye = filterLaye;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean istpk() {
        return istpk;
    }

    public void setIstpk(boolean istpk) {
        this.istpk = istpk;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLayertype() {
        return layertype;
    }

    public void setLayertype(String layertype) {
        this.layertype = layertype;
    }

    public String getMarkerICon() {
        return markerICon;
    }

    public void setMarkerICon(String markerICon) {
        this.markerICon = markerICon;
    }

    public String getOfflineMapsUrl() {
        return OfflineMapsUrl;
    }

    public void setOfflineMapsUrl(String offlineMapsUrl) {
        OfflineMapsUrl = offlineMapsUrl;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getReturnAttribute() {
        return ReturnAttribute;
    }

    public void setReturnAttribute(String returnAttribute) {
        ReturnAttribute = returnAttribute;
    }

    public String getSds() {
        return sds;
    }

    public void setSds(String sds) {
        this.sds = sds;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getStatisAttribute() {
        return statisAttribute;
    }

    public void setStatisAttribute(String statisAttribute) {
        this.statisAttribute = statisAttribute;
    }

    public String getStatisticsAttribute() {
        return statisticsAttribute;
    }

    public void setStatisticsAttribute(String statisticsAttribute) {
        this.statisticsAttribute = statisticsAttribute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }

    public String getWhereAttribute() {
        return whereAttribute;
    }

    public void setWhereAttribute(String whereAttribute) {
        this.whereAttribute = whereAttribute;
    }

    public boolean isbChecked() {
        return bChecked;
    }

    public void setbChecked(boolean bChecked) {
        this.bChecked = bChecked;
    }

    public float getAlphas() {
        return alphas;
    }

    public void setAlphas(float alphas) {
        this.alphas = alphas;
    }

    public Boolean getIsshowlayer() {
        return isshowlayer;
    }

    public void setIsshowlayer(Boolean isshowlayer) {
        this.isshowlayer = isshowlayer;
    }

    public Boolean getIssearchproperty() {
        return issearchproperty;
    }

    public void setIssearchproperty(Boolean issearchproperty) {
        this.issearchproperty = issearchproperty;
    }





    @Override
    public int compareTo(OnLineConfigNode another) {
        int result = this.order.compareTo(another.getOrder());
        return result;
    }
}
