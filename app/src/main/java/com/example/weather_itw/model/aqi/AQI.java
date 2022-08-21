package com.example.weather_itw.model.aqi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AQI {
    //api AQI v1版本停用(2022/05/19)
    @SerializedName("sort")
    private String sort;
    @SerializedName("include_total")
    private Boolean includeTotal;
    @SerializedName("resource_id")
    private String resourceId;
    @SerializedName("fields")
    private List<Fields> fields;
    @SerializedName("__extras")
    private Extras extras;
    @SerializedName("records_format")
    private String recordsFormat;
    @SerializedName("records")
    private List<Records> records;
    @SerializedName("limit")
    private Integer limit;
    @SerializedName("offset")
    private Integer offset;
    @SerializedName("_links")
    private Links links;
    @SerializedName("distribution")
    private List<Distribution> distribution;
    @SerializedName("total")
    private Integer total;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getIncludeTotal() {
        return includeTotal;
    }

    public void setIncludeTotal(Boolean includeTotal) {
        this.includeTotal = includeTotal;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    public String getRecordsFormat() {
        return recordsFormat;
    }

    public void setRecordsFormat(String recordsFormat) {
        this.recordsFormat = recordsFormat;
    }

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Distribution> getDistribution() {
        return distribution;
    }

    public void setDistribution(List<Distribution> distribution) {
        this.distribution = distribution;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public static class Extras {
        @SerializedName("api_key")
        private String apiKey;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }
    }

    public static class Links {
        @SerializedName("start")
        private String start;
        @SerializedName("next")
        private String next;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
    }

    public static class Fields {
        @SerializedName("info")
        private Info info;
        @SerializedName("type")
        private String type;
        @SerializedName("id")
        private String id;

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class Info {
            @SerializedName("notes")
            private String notes;
            @SerializedName("label")
            private String label;

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }
    }

    public static class Records {
        @SerializedName("SiteName")
        private String siteName;
        @SerializedName("County")
        private String county;
        @SerializedName("AQI")
        private String aqi;
        @SerializedName("Pollutant")
        private String pollutant;
        @SerializedName("Status")
        private String status;
        @SerializedName("SO2")
        private String so2;
        @SerializedName("CO")
        private String co;
        @SerializedName("CO_8hr")
        private String co8hr;
        @SerializedName("O3")
        private String o3;
        @SerializedName("O3_8hr")
        private String o38hr;
        @SerializedName("PM10")
        private String pm10;
        @SerializedName("PM2.5")
        private String pm25;
        @SerializedName("NO2")
        private String no2;
        @SerializedName("NOx")
        private String nOx;
        @SerializedName("NO")
        private String no;
        @SerializedName("WIND_SPEED")
        private String windSpeed;
        @SerializedName("WIND_DIREC")
        private String windDirec;
        @SerializedName("PublishTime")
        private String publishTime;
        @SerializedName("PM2.5_AVG")
        private String pm25Avg;//
        @SerializedName("PM10_AVG")
        private String pm10Avg;
        @SerializedName("SO2_AVG")
        private String so2Avg;
        @SerializedName("Longitude")
        private String longitude;
        @SerializedName("Latitude")
        private String latitude;
        @SerializedName("SiteId")
        private String siteId;
        @SerializedName("ImportDate")
        private String importDate;

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public Float getAqi() {
            float aqi_f = Float.parseFloat((aqi.equals("")||aqi.equals("-")?"0":aqi));
            return aqi_f;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getPollutant() {
            return pollutant;
        }

        public void setPollutant(String pollutant) {
            this.pollutant = pollutant;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSo2() {
            return (so2.equals("")||so2.equals("-"))?"N/A":so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getCo() {
            return (co.equals("")||co.equals("-"))?"N/A":co;
        }

        public void set(String co) {
            this.co = co;
        }

        public String getCo8hr() {
            return co8hr;
        }

        public void setCo8hr(String co8hr) {
            this.co8hr = co8hr;
        }

        public String getO3() {
            return (o3.equals("")||o3.equals("-"))?"N/A":o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getO38hr() {
            return (o38hr.equals("")||o38hr.equals("-"))?"N/A":o38hr;
        }

        public void setO38hr(String o38hr) {
            this.o38hr = o38hr;
        }

        public String getPm10() {
            return (pm10.equals("")||pm10.equals("-"))?"N/A":pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String get_Pm25() {
            float pm25_f = Float.parseFloat((pm25.equals("")||pm25.equals("-")?"0.0":pm25));

            return (pm25.equals("")||pm25.equals("-"))?"N/A":pm25;
        }

        public void set_Pm25(String Pm25) {
            this.pm25 = pm25;
        }

        public String getNo2() {
            return (no2.equals("")||no2.equals("-"))?"N/A":no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getNOx() {
            return nOx;
        }

        public void setNOx(String nOx) {
            this.nOx = nOx;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getWindDirec() {
            return windDirec;
        }

        public void setWindDirec(String windDirec) {
            this.windDirec = windDirec;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String get_Pm25Avg() {
            return pm25Avg;
        }

        public void set_Pm25Avg(String pm25Avg) {
            this.pm25Avg = pm25Avg;
        }

        public String getPm10Avg() {
            return pm10Avg;
        }

        public void setPm10Avg(String pm10Avg) {
            this.pm10Avg = pm10Avg;
        }

        public String getSo2Avg() {
            return so2Avg;
        }

        public void setSo2Avg(String so2Avg) {
            this.so2Avg = so2Avg;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getImportDate() {
            return importDate;
        }

        public void setImportDate(String importDate) {
            this.importDate = importDate;
        }
    }

    public static class Distribution {
        @SerializedName("qcLevel")
        private String qcLevel;

        public String getQcLevel() {
            return qcLevel;
        }

        public void setQcLevel(String qcLevel) {
            this.qcLevel = qcLevel;
        }
    }
}
