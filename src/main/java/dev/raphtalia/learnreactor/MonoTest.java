package dev.raphtalia.learnreactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

/**
 * Reactive Streams
 * 1. Asynchronous.
 * 2. Non-blocking.
 * 3. Backpressure.
 * Publisher <- (subscribe) Subscriber
 * Subscription is created
 * Publisher (onSubscribe with the subscription) -> Subscriber
 * Subscription <- (request N) Subscriber
 * Publisher -> (onNext) Subscriber
 * repeat until:
 * 1. Publisher sends all the objects requested.
 * 2. Publisher sends all the objects it has. (onComplete) subscriber and subscription will be canceled.
 * 3. There is an error. (onError) -> subscriber and subscription will be canceled
 */
public class MonoTest {

    public MonoTest() {
        System.out.println("MonoTest");
        MonoSubscriberConsumerSubscription();
    }

    public void MonoSubscriber() {
        String name = "Cameron Whyte";
        Mono<String> mono = Mono.just(name)
                .log();

        mono.subscribe();
        System.out.println("-----------------");//Call is separate
        mono.subscribe();
    }

    public void MonoSubscriberConsumer() {
        String name = "Cameron Whyte";
        Mono<String> mono = Mono.just(name)
                .log();

        mono.subscribe(System.out::println);
        System.out.println("-----------------");//Call is separate
        mono.subscribe(s -> System.out.printf("Value is %s\n", s));
    }

    public void MonoSubscriberConsumerError() {
        String name = "Cameron Whyte";
        Mono<String> mono = Mono.just(name)
                .map(s -> {
                    throw new RuntimeException("Testing Mono with error");
                });

        mono.subscribe(s -> System.out.printf("Name is %s\n", s), s -> System.out.println("Something bad occurred"));
        mono.subscribe(s -> System.out.printf("Name is %s\n", s), Throwable::printStackTrace);
    }

    public void MonoSubscriberConsumerComplete() {
        String name = "Cameron Whyte";
        Mono<String> mono = Mono.just(name)
                .log()
                .map(String::toUpperCase);

        mono.subscribe(s -> System.out.printf("Value is %s\n", s),
                Throwable::printStackTrace,
                () -> System.out.println("FINISHED!"));
    }

    public void MonoSubscriberConsumerSubscription() {
        String name = "Cameron Whyte";
        Mono<String> mono = Mono.just(name)
                .log()
                .map(String::toUpperCase);

        mono.subscribe(s -> System.out.printf("Value is %s\n", s),
                Throwable::printStackTrace,
                () -> System.out.println("FINISHED!"),
                Subscription::cancel);
    }
}
