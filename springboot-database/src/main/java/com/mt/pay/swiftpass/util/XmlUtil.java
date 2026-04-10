package com.mt.pay.swiftpass.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * XML 工具（从老项目迁移，去掉 Servlet 依赖）
 */
public class XmlUtil {

    /** Map 转 XML 字符串 */
    public static String toXml(Map<String, String> params) {
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        buf.append("<xml>");
        for (String key : keys) {
            buf.append("<").append(key).append(">");
            buf.append("<![CDATA[").append(params.get(key)).append("]]>");
            buf.append("</").append(key).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }

    /** SortedMap 转 XML（保持原有顺序） */
    public static String sortedMapToXml(SortedMap<String, String> params) {
        StringBuilder sb = new StringBuilder("<xml>");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (v != null && !v.isEmpty() && !"appkey".equals(k)) {
                sb.append("<").append(k).append(">").append(v).append("</").append(k).append(">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /** XML 字节数组转 Map */
    public static Map<String, String> toMap(byte[] xmlBytes, String charset) throws Exception {
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
        source.setEncoding(charset);
        Document doc = reader.read(source);
        return elementToMap(doc.getRootElement());
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> elementToMap(Element element) {
        Map<String, String> result = new HashMap<>();
        List<Element> els = element.elements();
        for (Element el : els) {
            result.put(el.getName().toLowerCase(), el.getText());
        }
        return result;
    }
}
