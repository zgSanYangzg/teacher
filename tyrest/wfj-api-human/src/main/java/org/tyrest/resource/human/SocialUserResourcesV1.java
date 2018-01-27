package org.tyrest.resource.human;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.constants.ParamConstants;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.StringUtil;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.security.validation.BeanValidation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.core.restevent.RestEventTrigger;
import org.tyrest.human.face.model.SocialUserModel;
import org.tyrest.human.face.service.SocialUserService;
import org.tyrest.notification.producer.Notifier;

/**
 * Created by ttsw on 2017/1/11.
 */
@RestController
@RequestMapping(value = "/1/human")
@TyrestResource(module = "human",value = "SocialUserResourcesV1", description = "社会人力管理")
public class SocialUserResourcesV1 extends BaseResources
{
    @Autowired
    SocialUserService suService;

    @Autowired
    Notifier notifier;

    @TyrstOperation(name = "findByPage",ApiLevel = APILevel.SUPERADMIN, description = "分页获取社会人力列表",needAuth = true)
    @RequestMapping(value = "/social", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<Page> findByPage(
            @RequestParam(value="userName", required = false) String userName,
            @RequestParam(value="userIdcard", required = false) String userIdcard,
            @RequestParam(value = ParamConstants.OFFSET,defaultValue = "0") int start,
            @RequestParam(value = ParamConstants.LENGTH,defaultValue = "10") int length,
            @RequestParam(value = ParamConstants.SIDX, required = false) String orderBy,
            @RequestParam(value = ParamConstants.SORT, required = false) String order
    ) throws Exception
    {
        return ResponseHelper.buildResponseModel(suService.findByPage(StringUtil.iso2UTF8(userName),userIdcard,orderBy,order,new Page(length,start)));
    }
    @TyrstOperation(name = "findSocialUser",ApiLevel = APILevel.SUPERADMIN, description = "单个查询社会人力",needAuth = true)
    @RequestMapping(value = "/social/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<SocialUserModel> findSocialUser(@PathVariable Long userId) throws Exception
    {
        SocialUserModel sm = suService.get(userId);
        return ResponseHelper.buildResponseModel(sm);
    }
   @TyrstOperation(name = "createSocialUser",ApiLevel = APILevel.SUPERADMIN, description = "创建社会人力",needAuth = true)
   @RequestMapping(value = "/social", method = RequestMethod.POST, headers = "Accept=application/json")
   @BeanValidation
   @RestEventTrigger(value = "saveSocialUser")
   public ResponseModel<SocialUserModel> createSocialUser(@RequestBody SocialUserModel socialUserModel) throws Exception
   {
       SocialUserModel sum = suService.saveEntity(socialUserModel);

       //RestEventHandler.attachEventSource("saveSocialUser",sum);

       return ResponseHelper.buildResponseModel(sum);
   }

    @TyrstOperation(name = "deleteSocialUser",ApiLevel = APILevel.SUPERADMIN, description = "删除社会人力",needAuth = true)
    @RequestMapping(value = "/social/{userId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseModel<String> deleteSocialUser(@PathVariable Long userId) throws Exception
   {
       suService.delete(userId);
       return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
   }


    @TyrstOperation(name = "updateSocialUser",ApiLevel = APILevel.SUPERADMIN, description = "更新社会人力",needAuth = true)
    @RequestMapping(value = "/social/{userId}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseModel<String> updateSocialUser(@PathVariable Long userId,@RequestBody SocialUserModel socialUserModel) throws  Exception
   {
       socialUserModel.setSequenceNBR(userId);
       suService.updateEntity(socialUserModel) ; ///sssss

       return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
   }
}
