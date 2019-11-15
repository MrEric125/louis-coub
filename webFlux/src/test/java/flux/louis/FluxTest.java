package flux.louis;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

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
//                .flatMap(words -> Flux.fromArray(words.split("")))
                .distinct()

                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE)
                        , (string, count) -> String.format("%2d. %s", count, string));
        stringFlux.subscribe(System.out::println);




    }
}
