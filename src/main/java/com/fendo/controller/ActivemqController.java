package com.fendo.controller;

import com.fendo.producer.queue.QueueSender;
import com.fendo.producer.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/18 16:28
 * @Description:
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {

    @Autowired
    QueueSender queueSender;

    @Autowired
    TopicSender topicSender;

    @ResponseBody
    @RequestMapping("queueSender")
    public String queueSender(@RequestParam("message") String message){
        String opt = "";
        try {
            queueSender.send("test.queue", message);
        } catch (Exception e){
            opt = e.getCause().toString();
        }
        return opt;
    }

    @ResponseBody
    @RequestMapping("topicSender")
    public String topicSender(@RequestParam("message")String message){
        String opt = "";
        try {
            topicSender.send("test.topic", message);
            opt = "suc";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }
        return opt;
    }
}
