package com.sdy.controller.test.mq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.sdy.domain.User;

/**
 * 阿里云MQ测试
 */
@Controller
@RequestMapping("/mq")
public class MqController {
	
	// 鉴权用 AccessKey，在阿里云服务器管理控制台创建
	private String ACCESSKEY="LTAISHUJbYex1O8K";
	// 鉴权用 SecretKey，在阿里云服务器管理控制台创建
	private String SECRETKEY="sdyLEKL9qlBOjP5fFevxKPIqpgqePm";
	 // 您在 MQ 控制台创建的 Producer ID
	private String PRODUCERID="PID_parking_order";
	 // 您在 MQ 控制台创建的 Consumer ID
	private String CONSUMERID="CID_parking_order";
	/**
	 * 发送一个消息到队列
	 */
	@RequestMapping("/setMsg2MQ")
	@ResponseBody
	public String setMsg2MQ(){
		 Properties properties = new Properties();
        // 您在 MQ 控制台创建的 Producer ID
        properties.put(PropertyKeyConst.ProducerId, PRODUCERID);
        // 鉴权用 AccessKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.AccessKey,ACCESSKEY);
        // 鉴权用 SecretKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, SECRETKEY);
        // 设置 TCP 接入域名（此处以公共云的公网接入为例）
        properties.put(PropertyKeyConst.ONSAddr,
          "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
        Producer producer = ONSFactory.createProducer(properties);
        // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
        producer.start();
        User u = new User();
        u.setId("123456");
        Message msg = new Message( //
            // 在控制台创建的 Topic，即该消息所属的 Topic 名称
            "rh_order",
            // Message Tag,
            // 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在 MQ 服务器过滤
            "order",
            // Message Body
            // 任何二进制形式的数据， MQ 不做任何干预，
            // 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
            serialize(u));
        // 设置代表消息的业务关键属性，请尽可能全局唯一，以方便您在无法正常收到消息情况下，可通过 MQ 控制台查询消息并补发
        // 注意：不设置也不会影响消息正常收发
        msg.setKey("ORDERID_100");
        // 发送消息，只要不抛异常就是成功
        try{
        	  
        	/**加上下面的就是延迟队列，表示10秒后才投递
        	 * long delayTime = System.currentTimeMillis() + 10000;
        	 *设置消息需要被投递的时间
        	 * msg.setStartDeliverTime(delayTime);
        	 */

            // 打印 Message ID，以便用于消息发送状态查询
            SendResult sendResult = producer.send(msg);
            System.out.println("发送消息成功 ID: " + sendResult.getMessageId());
	        // 在应用退出前，可以销毁 Producer 对象
	        // 注意：如果不销毁也没有问题
	        producer.shutdown();
        }catch (Exception e) {
        	e.printStackTrace();
        	return "发送失败";
		}
		return "Success";
	}
	
	
	/**
	 * 启动订阅者
	 * @param page
	 * @return
	 */
	@RequestMapping("/startMQ")
	public String startMQ(){
		Properties properties = new Properties();
        // 您在 MQ 控制台创建的 Consumer ID
        properties.put(PropertyKeyConst.ConsumerId,CONSUMERID);
        // 鉴权用 AccessKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.AccessKey,ACCESSKEY);
        // 鉴权用 SecretKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey,SECRETKEY);
        // 设置 TCP 接入域名（此处以公共云公网环境接入为例）
        properties.put(PropertyKeyConst.ONSAddr,
          "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("rh_order", "*", new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {
                System.out.println("接收到: " + message);
                User u=  (User)unserialize(message.getBody());
                System.out.println("id:"+u.getId());
                return Action.CommitMessage;
            }
        });
        consumer.start();
        System.out.println("Consumer Started");
		return "Success";
	}
	
	
	
	
	/**
	 * 对象序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
		//序列化
		baos = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		byte[] bytes = baos.toByteArray();
		return bytes;
		} catch (Exception e) {
		 
		}
		return null;
	}
	/**
	 * 对象反序列化
	 * @param object
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
		//反序列化
		bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
		} catch (Exception e) {
		 
		}
		return null;
	}

}
