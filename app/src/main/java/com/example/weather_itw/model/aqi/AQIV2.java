package com.example.weather_itw.model.aqi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AQIV2 {

    @SerializedName("fields")
    private List<Fields> fields;
    @SerializedName("resource_id")
    private String resourceId;
    @SerializedName("__extras")
    private Extras extras;
    @SerializedName("include_total")
    private Boolean includeTotal;
    @SerializedName("total")
    private Integer total;
    @SerializedName("resource_format")
    private String resourceFormat;
    @SerializedName("limit")
    private Integer limit;
    @SerializedName("offset")
    private Integer offset;
    @SerializedName("_links")
    private Links links;
    @SerializedName("records")
    private List<Records> records;

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    public Boolean getIncludeTotal() {
        return includeTotal;
    }

    public void setIncludeTotal(Boolean includeTotal) {
        this.includeTotal = includeTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getResourceFormat() {
        return resourceFormat;
    }

    public void setResourceFormat(String resourceFormat) {
        this.resourceFormat = resourceFormat;
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

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
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
        @SerializedName("id")
        private String id;
        @SerializedName("type")
        private String type;
        @SerializedName("info")
        private Info info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public static class Info {
            @SerializedName("label")
            private String label;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }
    }

    public static class Records {
        @SerializedName("sitename")
        private String sitename;
        @SerializedName("county")
        private String county;
        @SerializedName("aqi")
        private String aqi;
        @SerializedName("pollutant")
        private String pollutant;
        @SerializedName("status")
        private String status;
        @SerializedName("so2")
        private String so2;
        @SerializedName("co")
        private String co;
        @SerializedName("o3")
        private String o3;
        @SerializedName("o3_8hr")
        private String o38hr;
        @SerializedName("pm10")
        private String pm10;
        @SerializedName("pm2.5")
        private String pm25;
        @SerializedName("no2")
        private String no2;
        @SerializedName("nox")
        private String nox;
        @SerializedName("no")
        private String no;
        @SerializedName("wind_speed")
        private String windSpeed;
        @SerializedName("wind_direc")
        private String windDirec;
        @SerializedName("publishtime")
        private String publishtime;
        @SerializedName("co_8hr")
        private String co8hr;
        @SerializedName("pm2.5_avg")
        private String pm25Avg;
        @SerializedName("pm10_avg")
        private String pm10Avg;
        @SerializedName("so2_avg")
        private String so2Avg;
        @SerializedName("longitude")
        private String longitude;
        @SerializedName("latitude")
        private String latitude;
        @SerializedName("siteid")
        private String siteid;

        public String getSitename() {
            return sitename;
        }

        public void setSitename(String sitename) {
            this.sitename = sitename;
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

        public void setCo(String co) {
            this.co = co;
        }

        public String getO3() {
            return (o3.equals("")||o3.equals("-"))?"N/A":o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getO38hr() {
            return o38hr;
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

        public void set_pm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getNo2() {
            return (no2.equals("")||no2.equals("-"))?"N/A":no2;
        }
        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getNox() {
            return nox;
        }

        public void setNox(String nox) {
            this.nox = nox;
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

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public String getCo8hr() {
            return co8hr;
        }

        public void setCo8hr(String co8hr) {
            this.co8hr = co8hr;
        }

        public String get_pm25Avg() {
            return pm25Avg;
        }

        public void set_pm25Avg(String pm25Avg) {
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

        public String getSiteid() {
            return siteid;
        }

        public void setSiteid(String siteid) {
            this.siteid = siteid;
        }
    }
}
