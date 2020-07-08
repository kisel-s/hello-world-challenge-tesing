package framework.rest;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

public class ExtendedHeader implements Header {
    @Override
    public HeaderElement[] getElements() throws ParseException {
        return new HeaderElement[0];
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}
