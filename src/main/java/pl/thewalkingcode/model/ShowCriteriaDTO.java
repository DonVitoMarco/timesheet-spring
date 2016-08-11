package pl.thewalkingcode.model;

public class ShowCriteriaDTO {

    private String dataStart;
    private String dataEnd;

    public String getDataStart() {
        return dataStart;
    }

    public void setDataStart(String dataStart) {
        this.dataStart = dataStart;
    }

    public String getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd;
    }

    @Override
    public String toString() {
        return "ShowCriteriaDTO{" +
                "dataStart='" + dataStart + '\'' +
                ", dataEnd='" + dataEnd + '\'' +
                '}';
    }

}
