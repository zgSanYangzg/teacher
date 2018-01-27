# 微分金 基础业务框架demo
## 提交日志
> ##### 1.初始化项目源代码
> ##### 2.测试本地分支 
> ##### 3.测试本地分支2 











































# Fressapis基础业务框架
----------
> ##### 1.模块划分更清晰
> ##### 2.重写了基础的业务模块,包括商家模块,用户模块,认证授权模块
> ##### 3.框架部分包括两个模块,freeapis-core,freeapis-framework,freeapis-core是框架的内核模块,不侵入丝毫业务代码,内核可以在任何项目中使用,freeapis-framewrok是业务框架,只包含接口和数据定义,接口实现在各个子模块完成
## Freeapis代码规范
----------
> ##### 1.数据访问层接口,统一使用后缀"DAO"(大写),如:PublicUserDetailDAO.
> ##### 2.业务层接口,统一使用后缀"Service",如:AgencyService.实现类统一使用后缀"ServiceImpl",如AgencyServiceImpl
> ##### 3.常量统一使用大写字母+下划线命名
> ##### 4.DAO中如果数据要缓存,则要重写增删改查方法,使用如下命名:添加:insertWithCache(Entity),删除:deleteWithCache(Entity),更新:updateWithCache(Entity),获取单个:getWithCache(id),DAO中的自定义条件查询,查询单个实体,方法名前缀统一使用:findByXXX(XXX),分页查询统一使用
> ##### 5.DAO中,如果是单表查询,则不用写select * from table,直接书写条件语句即可.
> ##### 6.DAO中,分页直接调用paginate方法,传入查询条件和page对象,paginate方法将返回数据列表,并且page中的totalRows会被赋值,所以在自己的DAO中不必重新查询获取totalRows,DAO中分页的返回值统一定义为List,不要定义为Page,Page对象请自行在Service层或者Resource层中封装.
> ##### 7.DAO中,如果单个实体需要缓存,请直接调用Redis的single后缀的方法,比如,Redis.setSingle(value,...keyMembers),Redis.getSingle(Class,...keyMembers),原来的CacheType已删除不再使用,单个实体的缓存key的前缀统一用实体类型表示,比如要缓存PublicUser实体,则只需调用Redis.setSingle(entity,userId),如果缓存key由多部分拼接而成,也不用自行拼接,直接传入key的多个部分即可,比如要缓存AgencyUser,它的缓存key由agencyCode和userId两部分组成,则可直接调用Redis.setSingle(entity,agencyCode,userId),请注意,keyMembers参数是可变长的参数;获取单个缓存实体:Redis.getSingle(AgencyUser.class,agencyCode,userId);删除单个缓存实体:Redis.removeSingle(AgencyUser.class,agencyCode,userId);如果需要操作自定义的缓存数据,而非实体,比如session,全局锁对象,等非数据库实体的数据,请使用Redis中非single后缀的方法,keyMembers也是可变长参数.
> ##### 7.Service中,增删改查方法命名规则为:create+实体类名(不是model名称),delete+实体类名,update+实体类名,获取单个实体使用getById(id)
> ##### 8.Service中所有的查询方法统一使用get前缀,和DAO层的find前缀区分开;
> ##### 9.Service中的create,update方法必须返回Model对象.以提高前端展现的性能,和方便做日志.
> ##### 10.所有模块中,定义常量时请注意,如果是所有模块可能都会用到的公用常量,请定义在freeapis-framework模块的constants包下,如果是模块单独使用的常量.请只在模块内部定义的constants/模块名/xxxConstants中定义.
> ##### 11.书写Resource时请统一遵守:
> ##### a.所有的Resource方法头上的@ResourceDescription注解的所有属性值必须完整,特别是Description方法描述,使用汉字描述清楚;
> ##### b.所有的Resource方法的返回值ResponseModel的泛型必须明确指定,表示该资源返回什么类型的数据,让前端看文档时更加清晰.
> ##### c.所有的批量删除方法中,要删除的资源的id,请以数组的形式封装在请求体中,不要在url中用逗号拼接;
> ##### d.Resource层中的所有方法,必须保持高度清洁,所有的判断,逻辑代码请写到Service层,resource层只负责接收请求参数,最多处理 一下请求参数,因为,按照restful的规范,resource只是定义资源的地方,所以只能定义资源,不处理资源.
> ##### e.请不要在resource层中抛异常,要抛异常统一在service层中抛出.框架中有统一处理异常的机制,不必在resource层中明确抛出RestException,如果不得不在resource中断请求,请直接return ResponseModel,ResponseHelper中的方法足够使用.


