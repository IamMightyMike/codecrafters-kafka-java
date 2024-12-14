package dto;

public class Response {

    private int correlationId;

    private short error_code ;
    private int arrayLength ;
    private short apiKey;
    private short minVersion ;
    private short maxVersion ;
    private byte array_tagged_fields ;
    private int throttle_time ;
    private byte outer_tagged_fields ;


    public int getCorrelationId() {
        return correlationId;
    }

    public short getErrorCode() {
        return error_code;
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public short getApiKey() {
        return apiKey;
    }

    public short getMinVersion() {
        return minVersion;
    }

    public short getMaxVersion() {
        return maxVersion;
    }

    public byte getArrayTaggedFields() {
        return array_tagged_fields;
    }

    public int getThrottleTime() {
        return throttle_time;
    }

    public byte getOuterTaggedFields() {
        return outer_tagged_fields;
    }

    // Setters
    public void setCorrelationId(int correlationId) {
        this.correlationId = correlationId;
    }

    public void setErrorCode(short error_code) {
        this.error_code = error_code;
    }

    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    public void setApiKey(short apiKey) {
        this.apiKey = apiKey;
    }

    public void setMinVersion(short minVersion) {
        this.minVersion = minVersion;
    }

    public void setMaxVersion(short maxVersion) {
        this.maxVersion = maxVersion;
    }

    public void setArrayTaggedFields(byte array_tagged_fields) {
        this.array_tagged_fields = array_tagged_fields;
    }

    public void setThrottleTime(int throttle_time) {
        this.throttle_time = throttle_time;
    }

    public void setOuterTaggedFields(byte outer_tagged_fields) {
        this.outer_tagged_fields = outer_tagged_fields;
    }

    // Builder
    public static class Builder {
        private int correlationId;
        private short error_code;
        private int arrayLength;
        private short apiKey;
        private short minVersion;
        private short maxVersion;
        private byte array_tagged_fields;
        private int throttle_time;
        private byte outer_tagged_fields;

        public Builder setCorrelationId(int correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public Builder setErrorCode(short error_code) {
            this.error_code = error_code;
            return this;
        }

        public Builder setArrayLength(int arrayLength) {
            this.arrayLength = arrayLength;
            return this;
        }

        public Builder setApiKey(short apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder setMinVersion(short minVersion) {
            this.minVersion = minVersion;
            return this;
        }

        public Builder setMaxVersion(short maxVersion) {
            this.maxVersion = maxVersion;
            return this;
        }

        public Builder setArrayTaggedFields(byte array_tagged_fields) {
            this.array_tagged_fields = array_tagged_fields;
            return this;
        }

        public Builder setThrottleTime(int throttle_time) {
            this.throttle_time = throttle_time;
            return this;
        }

        public Builder setOuterTaggedFields(byte outer_tagged_fields) {
            this.outer_tagged_fields = outer_tagged_fields;
            return this;
        }

        public Response build() {
            Response response = new Response();
            response.correlationId = this.correlationId;
            response.error_code = this.error_code;
            response.arrayLength = this.arrayLength;
            response.apiKey = this.apiKey;
            response.minVersion = this.minVersion;
            response.maxVersion = this.maxVersion;
            response.array_tagged_fields = this.array_tagged_fields;
            response.throttle_time = this.throttle_time;
            response.outer_tagged_fields = this.outer_tagged_fields;
            return response;
        }
    }

    // Static factory method for the builder
    public static Builder builder() {
        return new Builder();
    }


}
