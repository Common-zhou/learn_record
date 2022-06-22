package com.zhou;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import java.util.concurrent.TimeUnit;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 还需要再改一下。弄懂这里面是为什么
 *
 * @author zhoubing
 * @since 2022/06/19 22:22
 */
public class ActivemqDemo {

  public static void main(String[] args) throws JMSException, InterruptedException {
    String url = "tcp://127.0.0.1:61616";
    String destinationStr = "test.topic";

    //Destination destination = new ActiveMQTopic(destinationStr);
    Destination destination = new ActiveMQQueue(destinationStr);

    thread(new ActiveMQProducer(url, destination), false);
    thread(new ActiveMQProducer(url, destination), false);
    thread(new ActiveMQProducer(url, destination), false);
    thread(new ActiveMQConsumer(url, destination), false);

    thread(new ActiveMQProducer(url, destination), false);
    thread(new ActiveMQProducer(url, destination), false);

    TimeUnit.SECONDS.sleep(2);
  }

  public static void thread(Runnable runnable, boolean daemon) {
    Thread brokerThread = new Thread(runnable);
    brokerThread.setDaemon(daemon);
    brokerThread.start();
  }

  public static class ActiveMQProducer implements Runnable {

    private final String url;
    private final Destination destination;

    public ActiveMQProducer(String url, Destination destination) {
      this.url = url;
      this.destination = destination;
    }

    @Override
    public void run() {
      try {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);

        ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
        conn.start();
        Session session = conn.createSession(false, AUTO_ACKNOWLEDGE);

        Destination topic = destination;

        MessageProducer producer = session.createProducer(topic);

        TextMessage message = session.createTextMessage(System.currentTimeMillis() + " message.");
        producer.send(message);

        session.close();
        conn.close();

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  public static class ActiveMQConsumer implements Runnable {

    private final String url;
    private final Destination destination;

    public ActiveMQConsumer(String url, Destination destination) {
      this.url = url;
      this.destination = destination;
    }

    @Override
    public void run() {
      try {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);

        ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
        conn.start();
        Session session = conn.createSession(false, AUTO_ACKNOWLEDGE);

        //Destination topic = new ActiveMQTopic(destination);
        Destination topic = destination;


        MessageConsumer consumer = session.createConsumer(topic);

        // 消费消息
        //consumeMessage(consumer);

        // 这是个listener 会一直监听 所以不用循环 这个是等着推送
        addListinerAndSleep(consumer);

        consumer.close();
        session.close();
        conn.close();

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private void consumeMessage(MessageConsumer consumer) throws JMSException {
      Message message = consumer.receive(1000);
      System.out.println("got message :[ " + message + " ]");
    }

    private void addListinerAndSleep(MessageConsumer consumer) throws JMSException, InterruptedException {
      consumer.setMessageListener(message -> System.out.println("got message:" + message));
      TimeUnit.SECONDS.sleep(1000);
    }
  }
}
