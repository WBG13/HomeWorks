package com.firstshop.Containers;

public class RequestResponseCard {
    private String request;
    private String name;
    private String respond;
    private String outputExcludedTargets;
    private String outputExcludedTable;

    public String getOutputExcludedTable() {
        return outputExcludedTable;
    }

    public void setOutputExcludedTable(String outputExcludedTable) {
        this.outputExcludedTable = outputExcludedTable;
    }

    public String getOutputExcludedTargets() {
        return outputExcludedTargets;
    }

    public void setOutputExcludedTargets(String outputExcludedTargets) {
        this.outputExcludedTargets = outputExcludedTargets;
    }

    public String getRequest() {
        return request;
    }

    public void addRequestPart(String request) {
        this.request.concat(request);
    }

    public String getRespond() {
        return respond;
    }

    public void addRespondePart(String respond) {
        this.respond.concat(respond);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
