package flux.louis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author jun.liu
 * @date 2020/8/14 10:06
 */
@Slf4j
public class RedisLettuceTest {

    private static StatefulRedisConnection<String, String> CONNECTION;

    private static RedisClient CLIENT;

    private static RedisCommands<String, String> COMMAND;

    private static RedisReactiveCommands<String, String> REACTIVE_COMMAND;


    @BeforeClass
    public static void beforeClass() {
        RedisURI redisUri = RedisURI.builder().withHost("localhost")
                .withPort(32102)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS)).build();
        CLIENT = RedisClient.create(redisUri);
        CONNECTION=CLIENT.connect();

        COMMAND = CONNECTION.sync();
        REACTIVE_COMMAND = CONNECTION.reactive();

    }

    @AfterClass
    public static void afterClass() {

        CONNECTION.close();
        CLIENT.shutdown();

    }

    @Test
    public void testSyncPing() {

        String ping = COMMAND.ping();

        assert ping.equalsIgnoreCase("pong");

    }

    @Test
    public void reactivePing() throws InterruptedException {

        Mono<String> ping = REACTIVE_COMMAND.ping();
        ping.subscribe(v -> log.info("Ping result:{}", v));
        Thread.sleep(1000);
    }
    @Test
    public void testReactiveFunctional() throws Exception {
        REACTIVE_COMMAND.multi().doOnSuccess(r -> {
            REACTIVE_COMMAND.set("counter", "1").doOnNext(log::info).subscribe();
            REACTIVE_COMMAND.incr("counter").doOnNext(c -> log.info(String.valueOf(c))).subscribe();
        }).flatMap(s -> REACTIVE_COMMAND.exec())
                .doOnNext(transactionResult -> log.info("Discarded:{}", transactionResult.wasDiscarded()))
                .subscribe();
        Thread.sleep(1000);
    }


}
