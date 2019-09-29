package com.concurrent;

import org.bouncycastle.util.Arrays;

import javax.annotation.concurrent.Immutable;
import java.math.BigInteger;

/**
 * @author louis
 * <p>
 * Date: 2019/9/29
 * Description:
 *
 * 如果这个类中没有使用Arrays.copyOf那么这个类就不是不可变对象
 */
@Immutable
public class ImmutableObj {
    private final BigInteger lastNum;
    private final BigInteger[] lastFactorys;

    public ImmutableObj(BigInteger lastNum, BigInteger[] lastFactorys) {
        this.lastNum = lastNum;
        this.lastFactorys = Arrays.copyOf(lastFactorys, lastFactorys.length);

    }

    public BigInteger[] getFactorys(BigInteger integer) {
        if (lastNum == null || !lastNum.equals(integer)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactorys, lastFactorys.length);
        }
    }
}
