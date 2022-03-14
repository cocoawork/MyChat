package top.cocoawork.wechat.common.util;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.classmap.ClassMap;
import com.github.dozermapper.core.fieldmap.FieldMap;
import top.cocoawork.wechat.common.exception.MyChatException;

public class BeanUtil {

    private static volatile Mapper mapper;

    /**
     * @Description: 对象属性拷贝，支持级联嵌套的复杂对象拷贝
     * @Param: [sourceObj：待拷贝对象, destObj：目标对象]
     * @return: void
     */
    public static void copyProperties(Object sourceObj, Object destObj){
        if (null == sourceObj || destObj == null) {
            throw new MyChatException("拷贝对象为null");
        }

        if (null == mapper) {
            synchronized (BeanUtil.class) {
                if (null == mapper) {
                    mapper = DozerBeanMapperBuilder.create().withCustomFieldMapper(
                            (Object source, Object destination, Object sourceFieldValue, ClassMap classMap, FieldMap fieldMapping)->sourceFieldValue == null
                    ).build();
                }
            }
        }
        mapper.map(sourceObj, destObj);
    }


}
