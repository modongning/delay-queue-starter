package com.otoomo.starter.delayqueue.demo;

import com.otoomo.starter.delayqueue.DelayMessage;

public class PkFinishDelayMessage extends DelayMessage {
    private Long pkId;

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }
}
