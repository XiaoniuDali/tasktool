package com.bymz.tasktool.common.dict;
import com.bymz.tasktool.comon.utils.SpringUtil;
import com.bymz.tasktool.modules.sys.dict.dao.SysDictDataDao;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class DictSerialize<T> extends JsonSerializer<T> implements ContextualSerializer {
    @Autowired
    private SysDictDataDao dictDataDao;
    String key = "";
    String name = "";
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
//            SysDictDataDao dictDataDao = (SysDictDataDao) SpringUtil.getBean("sysDictDataDao");
            List<SysDictData> sys_status = dictDataDao.queryByDictType("sys_status");
            gen.writeStartObject();
//            String info = CacheUtils.getDictInfo(this.key, String.valueOf(value));
            String info = "草稿";
            if (info != null) {
                gen.writeObjectField("label", info);
            }
            gen.writeObjectField("value", value);
            gen.writeEndObject();
        } else {
            gen.writeNull();
        }
    }

    @Override
    public JsonSerializer<T> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {
        Dict filterSerialize = (Dict) property.getAnnotation(Dict.class);
        if (filterSerialize == null) {
            throw new RuntimeException("未注解@Dict");
        } else {
            this.key = filterSerialize.key();
            return this;
        }
    }
}