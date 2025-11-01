package com.luml.java.data.json.fastjson.Serializer.e6yun;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiqiang@e6yun.com
 */
//@Slf4j
@SuppressWarnings("unused")
public class TmsBaseEnumCodec implements ObjectSerializer, ObjectDeserializer {

    @Override
    @SuppressWarnings("unchecked")
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;

        if (object instanceof List) {
            List<Object> list = (List<Object>) object;
            if (CollectionUtils.isEmpty(list)) {
                out.writeNull(SerializerFeature.WriteNullListAsEmpty);
                return;
            }

            /*
            "coStatusList":[{"id": 1, "label": "司机拒绝"},{"id": 1, "label": "司机拒绝"}]
             */
            out.write("[");
            out.write(list.stream()
                    .map(obj -> getSingleEnumStr((TmsBaseEnum) obj, out))
                    .collect(Collectors.joining(", "))
            );
            out.write("]");
        } else {
            TmsBaseEnum baseEnum = (TmsBaseEnum) object;
            if (baseEnum == null) {
                out.writeNull(SerializerFeature.WriteNullNumberAsZero);
                return;
            }
            /*
               "type": 1,
               "typeLabel": "一个Label"
             */
            out.writeInt(baseEnum.getId());
            out.write(String.format(", \"%s\": \"%s\"", fieldName + "Label", baseEnum.getLabel()));
        }
    }

    private String getSingleEnumStr(TmsBaseEnum obj, SerializeWriter out) {
        return String.format("{\"id\": %s, \"label\": \"%s\"}", obj.getId(), obj.getLabel());
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {

        try {
            Object value;
            final JSONLexer lexer = parser.lexer;
            final int token = lexer.token();

            if (token == JSONToken.LITERAL_INT) {
                value = lexer.intValue();
                lexer.nextToken(JSONToken.COMMA);
                return (T) TmsBaseEnumUtil.getById((int) value, (Class<Enum>) type);
            } else if (token == JSONToken.LITERAL_STRING) {
                value = lexer.stringVal();
                lexer.nextToken(JSONToken.COMMA);
                if (StringUtils.isNumeric(value.toString())) {
                    return (T) TmsBaseEnumUtil.getById(Integer.parseInt(value.toString()), (Class<Enum>) type);
                } else {
                    // 如果不是数字，尝试用枚举类的name()来换枚举类
                    Object e = TmsBaseEnumUtil.getByEnumName(value.toString(), (Class<Enum>) type);
                    if (Objects.nonNull(e)) {
                        return (T) e;
                    } else {
                        //log.warn("无法序列化为枚举类型：{}", value);
                        return null;
                    }
                }
            } else if (token == JSONToken.NULL) {
                value = null;
                lexer.nextToken(JSONToken.COMMA);
                return null;
            }  else if (type == JSONArray.class) {
                /*
                 * 集合类型参考
                 * @see com.alibaba.fastjson.serializer.CollectionCodec
                 */
                JSONArray array = new JSONArray();
                parser.parseArray(array);

                JSONArray enumArray = new JSONArray();
                for (Object obj : array) {
                    enumArray.add(TmsBaseEnumUtil.getById(Integer.parseInt(obj.toString()), (Class<Enum>) type));
                }
                return (T) enumArray;
            } else if (token == JSONToken.SET || token == JSONToken.LBRACKET) {
                /*
                 * 集合类型参考
                 * @see com.alibaba.fastjson.serializer.CollectionCodec
                 */
                Collection list;
                if (parser.lexer.token() == JSONToken.SET) {
                    parser.lexer.nextToken();
                    list = TypeUtils.createSet(type);
                } else {
                    list = TypeUtils.createCollection(type);
                }
                List<Integer> idList = Lists.newArrayList();
                Type itemType = TypeUtils.getCollectionItemType(type);
                parser.parseArray(Integer.class, idList, fieldName);
                for (Integer id : idList) {
                    list.add(TmsBaseEnumUtil.getById(id, (Class) itemType));
                }
                return (T) list;

            } else {
                value = parser.parse();
            }

            throw new JSONException("parse enum " + type.getTypeName() + " error, value : " + value);

        } catch (JSONException e) {
            throw e;
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }

    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }


}
