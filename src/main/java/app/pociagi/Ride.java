package app.pociagi;

import javax.xml.transform.Source;

public class Ride {
    private String source;
    private String destination;

    public Ride() {}

    public Ride(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
