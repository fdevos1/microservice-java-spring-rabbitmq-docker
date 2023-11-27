package github.fdevos.mscreditappraiser.application.ex;

import lombok.Getter;

public class MicroserviceCommunicationErrorException extends Exception{

    @Getter
    private Integer status;
    public MicroserviceCommunicationErrorException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
