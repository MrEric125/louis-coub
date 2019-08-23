package com.louis.effective;

import com.google.common.collect.Lists;
import lombok.NonNull;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author louis
 * <p>
 * Date: 2019/7/16
 * Description:
 */
public class LambdaTest {


    @Test
    public void test() {

        List<String> list = Lists.newArrayList();
        list.add("zhangsan");

        list.add("wangwu");

        list.add("zhaoliu");
        list.add("tianqi");
        List<String> list1 = list.subList(0, list.size() );
        list1.forEach(System.out::println);

        List<String> put = put(null);
        System.out.println(put.size());

        Stream stream = Stream.of("zhangsan");
        Optional optional = Optional.of("zhangsan");
    }

    public long pi(long n) {
        return LongStream.rangeClosed(2, n).mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50)).count();
    }
    public long pi2(long n) {
        return LongStream.rangeClosed(2, n).parallel().mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50)).count();
    }

    public List<String> put(@NotNull List<String> s) {
        return new ArrayList<>(s);
    }

    public void serivable() {
        try {
            InputStream is = new FileInputStream("");
            ObjectInputStream ios = new ObjectInputStream(is);
            Object object = ios.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
