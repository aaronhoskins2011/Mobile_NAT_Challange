
package com.example.aaronhoskins.mobile_nat_challange.model.forecast;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features implements Serializable
{

    @SerializedName("hourly10day")
    @Expose
    private Integer hourly10day;
    private final static long serialVersionUID = -6508455287796112507L;

    public Integer getHourly10day() {
        return hourly10day;
    }

    public void setHourly10day(Integer hourly10day) {
        this.hourly10day = hourly10day;
    }

}
