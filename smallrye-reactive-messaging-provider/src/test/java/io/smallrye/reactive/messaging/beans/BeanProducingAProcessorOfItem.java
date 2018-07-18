package io.smallrye.reactive.messaging.beans;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.DefaultMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.ReactiveStreams;
import org.reactivestreams.Processor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeanProducingAProcessorOfItem {

  @Incoming(topic = "count")
  @Outgoing(topic = "sink")
  public Processor<Integer, String> process() {
    return ReactiveStreams.<Integer>builder()
      .map(i -> i + 1)
      .flatMapPublisher(i -> Flowable.just(i, i))
      .map(i -> Integer.toString(i))
      .buildRs();
  }

}