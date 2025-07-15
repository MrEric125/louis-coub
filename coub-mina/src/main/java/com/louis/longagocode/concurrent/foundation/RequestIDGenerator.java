package com.louis.longagocode.concurrent.foundation;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author John·Louis
 * @date created on 2020/2/2
 * description:
 * 一个竞态的典型例子
 */
public class RequestIDGenerator implements CircularSeqGenerator {


    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();

    private final static short SEQ_UPPER_LIMIT = 999;

    private short sequence = -1;

    private RequestIDGenerator() {

    }

    @Override
    public short nextSequence() {
        if (sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }

    /**
     * @return
     */
    @Override
    public String nextID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        DecimalFormat df = new DecimalFormat("000");
        short sequence = nextSequence();
        return "0049" + timestamp + df.format(sequence);
    }

    public static RequestIDGenerator getInstance() {
        return INSTANCE;
    }

}
