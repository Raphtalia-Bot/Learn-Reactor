# Learn Project-Reactor
Here you can see the meanings of the most used methods in Project Reactor.

## Mono
A Reactive Streams Publisher with basic rx operators that emits at most one item via the onNext signal then terminates with an onComplete signal (successful Mono, with or without value), or only emits a single onError signal (failed Mono).
### Mono.just(arg)
Just requires an object to be inputted, this then starts off your Mono using your arg.
### .log() or .log(LOGGER)
You can use a logging library or leave the args empty. This creates a console output in your project when it is used.
### .map()
Map gives you the value the Mono contains which you can transform. For example Mono contains a string so in map you can String::toUpperCase to make the output of the Mono an Uppercase version of the original.
### .subscribe()
Subscribe allows you to run the Mono, you can then receive its end value .subscribe(System.out::println).
### .doOnSubscribe()
DoOnSubscribe allows you to do stuff the moment subscribe is called on the Mono.
### .doOnRequest()
DoOnRequest allows you to do stuff the moment someone calls request on the Mono.
### .doOnNext()
Triggered when the Mono emits the data successfully, it gives you the value of the Mono.
### .doOnSuccess()
Triggered when the Mono has been completed successfully, it gives you the value of the Mono.