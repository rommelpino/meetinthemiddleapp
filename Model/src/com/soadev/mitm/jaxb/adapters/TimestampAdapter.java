package com.soadev.mitm.jaxb.adapters;

import java.sql.Timestamp;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {

    public Date marshal(Timestamp value) {
        return new Date(value.getTime());
    }

    public Timestamp unmarshal(Date value) {
        return new Timestamp(value.getTime());
    }
}
