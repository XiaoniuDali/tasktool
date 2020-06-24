package com.bymz.tasktool.common.dialect;
import com.bymz.tasktool.comon.utils.SpringUtil;
import com.bymz.tasktool.modules.sys.dict.dao.SysDictDataDao;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import com.bymz.tasktool.modules.sys.dict.service.SysDictDataService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 自定义字典标签
 */
public class SysDictTagProcessor extends AbstractElementTagProcessor {
    // 标签名
    private static final String TAG_NAME = "dict";
    // 优先级
    private static final int PRECEDENCE = 10000;

    public SysDictTagProcessor(String dialectPrefix) {
        super(
                TemplateMode.HTML,// 此处理器将仅应用于HTML模式
                dialectPrefix,// 要应用于名称的匹配前缀
                TAG_NAME,// 标签名称：匹配此名称的特定标签
                true,// 将标签前缀应用于标签名称
                null,// 无属性名称：将通过标签名称匹配
                false,// 没有要应用于属性名称的前缀
                PRECEDENCE// 优先(内部方言自己的优先)
        );
    }

    /**
     * 处理自定义标签 DOM 结构
     *
     * @param iTemplateContext            模板页上下文
     * @param iProcessableElementTag      待处理标签
     * @param iElementTagStructureHandler 元素标签结构处理器
     */
    @Override
    protected void doProcess(ITemplateContext iTemplateContext,
                             IProcessableElementTag iProcessableElementTag,
                             IElementTagStructureHandler iElementTagStructureHandler) {
        //获取标签的属性值
        String divClass = iProcessableElementTag.getAttributeValue("divClass");
//        String dictType = iProcessableElementTag.getAttributeValue("type");
        String dictName = iProcessableElementTag.getAttributeValue("dictName");
        String name = iProcessableElementTag.getAttributeValue("name");
        String dictValue = iProcessableElementTag.getAttributeValue("value");
        String dictClass = iProcessableElementTag.getAttributeValue("class");
        String fastOptionValue = iProcessableElementTag.getAttributeValue("fastOptionValue");
        String fastOptionName = iProcessableElementTag.getAttributeValue("fastOptionName");

        //查询数据库
        //设置参数
//        Map<String,Object> paraMap = new HashMap<String,Object>();
//        paraMap.put("dict_type", dictType);

        // 获取 Spring上下文
//        ApplicationContext applicationContext = SpringContextUtils.getApplicationContext(iTemplateContext);
        //获取字典service的bean
//        DictionaryService dictService = applicationContext.getBean(DictionaryService.class);
        SysDictDataService sysDictDataService = (SysDictDataService) SpringUtil.getBean("sysDictDataServiceImpl");

        // 根据类型查询出字典列表
        List<SysDictData> dictList = sysDictDataService.queryByDictType(dictName);

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();

        model.add(modelFactory.createOpenElementTag(String.format("div class='%s'", divClass)));
        // 这里是将字典的内容拼装成一个下拉框
        model.add(modelFactory.createOpenElementTag(String.format("select name='%s' class='%s'", name, dictClass)));
        if(StringUtils.isNotBlank(fastOptionName)){
            if(StringUtils.isNotBlank(fastOptionValue)){
                model.add(modelFactory.createOpenElementTag(String.format("option value='%s'", fastOptionValue)));
            }else{
                model.add(modelFactory.createOpenElementTag("option value=''"));
            }
            model.add(modelFactory.createText(fastOptionName));
            model.add(modelFactory.createCloseElementTag("option"));
        }


        for (SysDictData dict : dictList) {
            boolean be = StringUtils.isNotBlank(dictValue) && dictValue.equals(dict.getDictValue());
            String str = be ? "option selected='selected' value='%s'" : "option value='%s'";
            model.add(modelFactory.createOpenElementTag(String.format(str, dict.getDictValue())));
            model.add(modelFactory.createText(dict.getDictLabel()));
            model.add(modelFactory.createCloseElementTag("option"));
        }
        model.add(modelFactory.createCloseElementTag("select"));

        model.add(modelFactory.createCloseElementTag("div"));
        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}