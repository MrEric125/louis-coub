package flux.louis;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author louis
 * <p>
 * Date: 2019/11/13
 * Description:
 */
public class FluxTest {

    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5, 6);
        integerFlux.subscribe(System.out::print, System.out::println, () -> System.out.print("completed"));
        System.out.println();

        Flux<String> stringFlux = Flux.fromIterable(words)
                .flatMap(words -> Flux.fromArray(words.split("")))
                .distinct()

                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE)
                        , (string, count) -> String.format("%2d. %s", count, string));
        stringFlux.subscribe(System.out::println);


    }
    @Test
    public void  test() {
        Map<String, String> map = Maps.newHashMap();
        map.put("zhangsan", "zzz");
        map.put("lisi", "lll");
        map.put("zhaoliu", "zzz");


        List<String> keyList = map
                .entrySet()
                .stream()
                .filter(x -> StringUtils.equals("zzz", x.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Map<String, String> map1 = Maps.filterEntries(map, entry -> StringUtils.equals("zzz", entry != null ? entry.getValue() : null));
        map1.forEach((key, value) -> System.out.println(key));

        keyList.forEach(System.out::println);

    }
}
