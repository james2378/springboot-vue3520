package com.project.demo.controller;

import com.project.demo.entity.RecommendedInformation;
import com.project.demo.service.RecommendedInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *推荐信息：(RecommendedInformation)表控制层
 *
 */
@RestController
@RequestMapping("/recommended_information")
public class RecommendedInformationController extends BaseController<RecommendedInformation,RecommendedInformationService> {

    /**
     *推荐信息对象
     */
    @Autowired
    public RecommendedInformationController(RecommendedInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
