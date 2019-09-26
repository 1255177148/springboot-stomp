package com.websocket.demo.entity;

/**
 * @Author hezhan
 * @Date 2019/9/25 15:40
 * 通信消息规范格式
 */
public class Message {
    private String message;//消息内容
    private String datetime;//发送时间
    private String from;//消息来源ID
    private String to;//发送消息给ID

    public Message(String message, String datetime, String from, String to) {
        this.message = message;
        this.datetime = datetime;
        this.from = from;
        this.to = to;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
