package umn.ac.bigboss.modelauth;

import java.util.List;

public class FCMModel {
    private int multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;
    private List<IsiMessageFCM> results;

    public int getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(int multicast_id) {
        this.multicast_id = multicast_id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(int canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public List<IsiMessageFCM> getResults() {
        return results;
    }

    public void setResults(List<IsiMessageFCM> results) {
        this.results = results;
    }
}

class IsiMessageFCM {
    public String message_id;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }
}
