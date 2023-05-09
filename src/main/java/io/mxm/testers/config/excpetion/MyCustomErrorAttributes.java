package io.mxm.testers.config.excpetion;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyCustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(
            //  "timestamp": 1683646396478,
            //    "status": 404,
            //    "message": "No message available",
            //    "path": "/admin/",
            //    "locale": "en_US"

            WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes =
                super.getErrorAttributes(webRequest, options);
        errorAttributes.put("data", null);

        errorAttributes.put("error", errorAttributes.get("status")+" "+errorAttributes.get("error"));
        errorAttributes.remove("timestamp");
        errorAttributes.remove("status");
        errorAttributes.remove("message");
        errorAttributes.remove("path");
        errorAttributes.remove("locale");


        //...

        return errorAttributes;
    }
}