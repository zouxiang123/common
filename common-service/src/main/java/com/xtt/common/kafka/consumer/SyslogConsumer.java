/**   
 * @Title: ProcessHistConsumer.java 
 * @Package KafkaConsumer
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月15日 下午4:14:27 
 *
 */
package com.xtt.common.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.SysLog;
import com.xtt.platform.util.io.JsonUtil;

@Component
public class SyslogConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyslogConsumer.class);

    @Autowired
    private ISysLogService sysLogService;

    @KafkaListener(id = "sysLogListener", topics = CommonConstants.TOPIC_SYS_LOG)
    public void onMessage(ConsumerRecord<Integer, String> record, Acknowledgment acknowledgment) {
        LOGGER.info("Received message topic" + record.topic() + ",message value is" + record.value());
        if (!CommonConstants.TOPIC_SYS_LOG.equals(record.topic()))
            return;
        try {
            SysLog log = JsonUtil.AllJsonUtil().fromJson(record.value(), SysLog.class);
            sysLogService.insert(log);
        } catch (Exception e) {
            LOGGER.info("convert data failed,value is" + record.value(), e);
            e.printStackTrace();
        }
        acknowledgment.acknowledge();
    }

}
