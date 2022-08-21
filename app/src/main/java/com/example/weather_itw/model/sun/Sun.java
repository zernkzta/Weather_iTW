package com.example.weather_itw.model.sun;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sun {

    @SerializedName("success")
    private String success;
    @SerializedName("result")
    private Result result;
    @SerializedName("records")
    private Records records;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }

    public static class Result {
        @SerializedName("resource_id")
        private String resourceId;
        @SerializedName("fields")
        private List<Fields> fields;

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

        public static class Fields {
            @SerializedName("id")
            private String id;
            @SerializedName("type")
            private String type;

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
        }
    }

    public static class Records {
        @SerializedName("dataid")
        private String dataid;
        @SerializedName("note")
        private String note;
        @SerializedName("locations")
        private Locations locations;

        public String getDataid() {
            return dataid;
        }

        public void setDataid(String dataid) {
            this.dataid = dataid;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Locations getLocations() {
            return locations;
        }

        public void setLocations(Locations locations) {
            this.locations = locations;
        }

        public static class Locations {
            @SerializedName("location")
            private List<Location> location;

            public List<Location> getLocation() {
                return location;
            }

            public void setLocation(List<Location> location) {
                this.location = location;
            }

            public static class Location {
                @SerializedName("locationName")
                private String locationName;
                @SerializedName("time")
                private List<Time> time;

                public String getLocationName() {
                    return locationName;
                }

                public void setLocationName(String locationName) {
                    this.locationName = locationName;
                }

                public List<Time> getTime() {
                    return time;
                }

                public void setTime(List<Time> time) {
                    this.time = time;
                }

                public static class Time {
                    @SerializedName("dataTime")
                    private String dataTime;
                    @SerializedName("parameter")
                    private List<Parameter> parameter;

                    public String getDataTime() {
                        return dataTime;
                    }

                    public void setDataTime(String dataTime) {
                        this.dataTime = dataTime;
                    }

                    public List<Parameter> getParameter() {
                        return parameter;
                    }

                    public void setParameter(List<Parameter> parameter) {
                        this.parameter = parameter;
                    }

                    public static class Parameter {
                        @SerializedName("parameterName")
                        private String parameterName;
                        @SerializedName("parameterValue")
                        private String parameterValue;

                        public String getParameterName() {
                            return parameterName;
                        }

                        public void setParameterName(String parameterName) {
                            this.parameterName = parameterName;
                        }

                        public String getParameterValue() {
                            return (parameterValue.equals("")||parameterValue.equals("-")?"N/A":parameterValue);
                        }

                        public void setParameterValue(String parameterValue) {
                            this.parameterValue = parameterValue;
                        }
                    }
                }
            }
        }
    }
    public String get_SunRise_Time(){
        return this.getRecords().getLocations().getLocation().get(0)
                .getTime().get(0).getParameter().get(0).getParameterValue();
    }
    public String get_SunSet_Time(){
        return this.getRecords().getLocations().getLocation().get(0)
                .getTime().get(0).getParameter().get(7).getParameterValue();
    }
}
