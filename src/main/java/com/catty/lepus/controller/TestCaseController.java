//package com.catty.lepus.controller;
//
//import com.catty.lepus.dto.testcase.CreateCaseDto;
//import com.catty.lepus.service.CaseService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @Description TODO
// * @Author catty
// * @Date 2022/11/15 22:59
// **/
//@Slf4j
//@Api(tags = "测试用例管理平台-用例管理")
//@RestController
//@RequestMapping("/case")
//public class TestCaseController {
//
//    //    @Autowired
//    @Resource
//    private CaseService caseService;
//
//    /**
//     * 列表 - 创建或者复制用例
//     *
//     * @param request 请求体
//     * @return 响应体
//     */
//    @ApiOperation(value = "列表 - 创建或者复制用例", notes = "返回创建或复制结果")
//    @PostMapping(value = "/create")
//    public Long createOrCopyCase(@RequestBody CreateCaseDto request) {
//        request.validate();
//        try {
//            return caseService.insertOrDuplicateCase(request);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("[Case Create]Create or duplicate test case failed. params={}, e={} ", request.toString(), e.getMessage());
//            return 1L;
//        }
//    }
//}
