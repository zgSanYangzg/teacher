package org.tyrest.resource.account;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;

@RestController
@RequestMapping(value = "/1/score")
@TyrestResource(module = "score",value = "ScoreResourcesV1", description = "积分信息")

public class ScoreResourcesV1 extends BaseResources
{

    //积分账户明细记录
    //积分余额



}
/*
 * $Log: av-env.bat,v $
 */