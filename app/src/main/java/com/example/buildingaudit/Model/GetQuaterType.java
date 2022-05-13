package com.example.buildingaudit.Model;

public class GetQuaterType {
    String RowNumber,PeriodId,Period,PeriodText,DateFrom,DateTo,Sequence,Activeated,SessionId;

    public GetQuaterType() {
    }

    public GetQuaterType(String rowNumber, String periodId, String period, String periodText, String dateFrom, String dateTo, String sequence, String activeated, String sessionId) {
        RowNumber = rowNumber;
        PeriodId = periodId;
        Period = period;
        PeriodText = periodText;
        DateFrom = dateFrom;
        DateTo = dateTo;
        Sequence = sequence;
        Activeated = activeated;
        SessionId = sessionId;
    }

    public String getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(String rowNumber) {
        RowNumber = rowNumber;
    }

    public String getPeriodId() {
        return PeriodId;
    }

    public void setPeriodId(String periodId) {
        PeriodId = periodId;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getPeriodText() {
        return PeriodText;
    }

    public void setPeriodText(String periodText) {
        PeriodText = periodText;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }

    public String getSequence() {
        return Sequence;
    }

    public void setSequence(String sequence) {
        Sequence = sequence;
    }

    public String getActiveated() {
        return Activeated;
    }

    public void setActiveated(String activeated) {
        Activeated = activeated;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }
}
