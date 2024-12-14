package dto;

public class Request {

    private int length ;

    //Header Fields
    private short apikey ;
    private short apiVersion ;

    private int correlationId ;

    public int getLength() {
        return length;
    }

    public short getApikey() {
        return apikey;
    }

    public short getApiVersion() {
        return apiVersion;
    }

    public int getCorrelationId() {
        return correlationId;
    }

    // Setters
    public void setLength(int length) {
        this.length = length;
    }

    public void setApikey(short apikey) {
        this.apikey = apikey;
    }

    public void setApiVersion(short apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setCorrelationId(int correlationId) {
        this.correlationId = correlationId;
    }

    // Builder
    public static class Builder {
        private int length;
        private short apikey;
        private short apiVersion;
        private int correlationId;

        public Builder setLength(int length) {
            this.length = length;
            return this;
        }

        public Builder setApikey(short apikey) {
            this.apikey = apikey;
            return this;
        }

        public Builder setApiVersion(short apiVersion) {
            this.apiVersion = apiVersion;
            return this;
        }

        public Builder setCorrelationId(int correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public Request build() {
            Request request = new Request();
            request.length = this.length;
            request.apikey = this.apikey;
            request.apiVersion = this.apiVersion;
            request.correlationId = this.correlationId;
            return request;
        }
    }

    // Static factory method for the builder
    public static Builder builder() {
        return new Builder();
    }


}
