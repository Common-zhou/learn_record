package com.zhou;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import java.util.concurrent.TimeUnit;

import javax.jms.Destination;
import javax.jms.JMSException;
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
    thread(new ActiveMQProducer(), false);
    thread(new ActiveMQProducer(), false);
    thread(new ActiveMQProducer(), false);
    thread(new ActiveMQConsumer(), false);

    thread(new ActiveMQProducer(), false);
    thread(new ActiveMQProducer(), false);

    //thread(new ActiveMQConsumer(), false);
    TimeUnit.SECONDS.sleep(2);
  }

  public static void thread(Runnable runnable, boolean daemon) {
    Thread brokerThread = new Thread(runnable);
    brokerThread.setDaemon(daemon);
    brokerThread.start();
  }

  public static class ActiveMQProducer implements Runnable {
    @Override
    public void run() {
      try {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
        conn.start();
        Session session = conn.createSession(false, AUTO_ACKNOWLEDGE);

        Destination topic = new ActiveMQTopic("test.topic");

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
    @Override
    public void run() {
      try {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
        conn.start();
        Session session = conn.createSession(false, AUTO_ACKNOWLEDGE);

        Destination topic = new ActiveMQTopic("test.topic");

        MessageConsumer consumer = session.createConsumer(topic);

        //Message message = consumer.receive(1000);
        //
        //System.out.println("got message :[ " + message + " ]");

        consumer.setMessageListener(message -> System.out.println("got message:" + message));

        TimeUnit.SECONDS.sleep(1000);

        consumer.close();
        session.close();
        conn.close();

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
