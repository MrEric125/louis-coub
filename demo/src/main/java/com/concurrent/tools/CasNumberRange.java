package com.concurrent.tools;

import javax.annotation.concurrent.Immutable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author louis
 * <p>
 * Date: 2019/9/5
 * Description:
 */
public class CasNumberRange {
    @Immutable
    public static class IntPair{
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values =
            new AtomicReference<>(new IntPair(0, 0));
    public int getLower() {
        return values.get().lower;
    }
    public int getUpper() {
        return values.get().upper;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i>oldv.upper) {
                throw new IllegalArgumentException(
                        "can't set lover(" + i + ")bigger than upper(" + oldv.upper + ")");
            }
            IntPair newv = new IntPair(i, oldv.upper);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }

        }

    }


}
