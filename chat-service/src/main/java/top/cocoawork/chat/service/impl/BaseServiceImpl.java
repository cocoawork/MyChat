package top.cocoawork.chat.service.impl;

import top.cocoawork.chat.common.util.BeanUtil;
import top.cocoawork.chat.service.api.exception.ChatServiceException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author cocoawork
 */
abstract public class BaseServiceImpl<D, DTO> {

    private final Class<D> dClass = getClass(0);
    private final Class<DTO> dtoClass = getClass(1);


    public void d2dto(D d, DTO dto) {
        BeanUtil.copyProperties(d, dto);
    }

    public void dto2d(DTO dto, D d) {
        BeanUtil.copyProperties(dto, d);
    }

    public DTO d2dto(D d) {
        try {
            DTO dto = dtoClass.newInstance();
            d2dto(d, dto);
            return dto;
        } catch (InstantiationException e) {
            throw new ChatServiceException("d2dto 转换出错",e);
        } catch (IllegalAccessException e) {
            throw new ChatServiceException("d2dto 转换出错",e);
        }
    }

    public D dto2d(DTO dto) {
        try {
            D d = dClass.newInstance();
            dto2d(dto, d);
            return d;
        } catch (InstantiationException e) {
            throw new ChatServiceException("dto2d 转换出错",e);
        } catch (IllegalAccessException e) {
            throw new ChatServiceException("dto2d 转换出错",e);
        }
    }


    private final Class getClass(int index) {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = null;
        if (superclass instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType)superclass;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null && types.length >= (1+index) ) {
                return (Class) types[index];
            }

        }
        return null;
    }

}
