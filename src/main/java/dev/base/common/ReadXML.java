package dev.base.common;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class ReadXML {

    public void readXML() {
        try {
            //1.创建Reader对象
            SAXReader reader = new SAXReader();
            //2.加载xml
            Document document = reader.read(ResourceUtils.getFile("classpath:config/module.xml"));
            //3.获取根节点
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                //得到所有的二级节点
                Element element = (Element) iterator.next();
                List<Element> elements = element.elements();
                for (Element elm : elements) {
                    //System.out.println(elm.attributeValue("name"));

                    //得到三级节点
                    Iterator iterator2=elm.elementIterator();
                    while (iterator2.hasNext()){
                        Element elementChild= (Element)iterator2.next();
                        //System.out.println(elementChild.attributeValue("name"));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("module.xml路径错误");
        } catch (DocumentException e1) {
            e1.printStackTrace();
            log.error("module.xml读取失败");
        }
    }
}
