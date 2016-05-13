package com.tmt.common;

public enum DataField {

    owner("2"), broker("3"), officeassistant("4"), fitter("5");

    String dataFieldName;

    DataField(String fieldName) {
        dataFieldName = fieldName;
    }

    public String toString() {
        return dataFieldName;
    }
}
