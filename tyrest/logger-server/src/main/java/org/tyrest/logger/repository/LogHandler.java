package org.tyrest.logger.repository;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.utils.JsonDateValueProcessor;
import org.tyrest.core.foundation.utils.JsonLongValueProcessor;
import org.tyrest.core.logger.LogModel;

import java.util.Date;
import java.util.UUID;

/**
 * freeapis, Inc.
 * Copyright (C): 2016
 * <p>
 * Description:
 * TODO
 * <p>
 * Author:Administrator
 * Date:2016年11月04日 15:24
 */
@Component
public class LogHandler {
    private static final Logger logger = LoggerFactory.getLogger(LogHandler.class);

    @Autowired
    private LogDAO logDAO;

    private JsonConfig jsonConfig;

    public LogHandler(){
        jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Long.class, new JsonLongValueProcessor());
    }

    @Async
    public void doLog(Object log){
        try {
            JSONArray logModelsJSON = JSONArray.fromObject(log,jsonConfig);
            for(int i = 0;i < logModelsJSON.size(); i++){
                JSONObject currentlogModelJson = logModelsJSON.getJSONObject(i);

                String logContent = currentlogModelJson.getString("logContent");
                currentlogModelJson.remove("logContent");

                String parameters = currentlogModelJson.getString("parameters");
                currentlogModelJson.remove("parameters");

                LogModel logContentModel = (LogModel) JSONObject.toBean(currentlogModelJson, LogModel.class);

                logContentModel.setLogContent(logContent);
                logContentModel.setParameters(parameters);
                logContentModel.setId(UUID.randomUUID().toString());
                logDAO.createIndex(logContentModel, logContentModel.getModel());
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
