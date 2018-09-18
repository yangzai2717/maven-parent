package com.pyy.activemq.rr;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/17 15:30
 * @Description: 此类需要运行上面的客户端/服务器示例。 将消息处理委托给单独的类仅仅是个人喜好。
 */
public class MessageProtocol {
    public String handleProtocolMessage(String messageText) {
        String responseText;
        // 判断是否是client传过来的信息，在这里就可以做些解析
        if ("MyProtocolMessage".equalsIgnoreCase(messageText)) {
            responseText = "我收到了信息";
        } else {
            responseText = "我不知道你传的是什么: " + messageText;
        }

        return responseText;
    }
}
