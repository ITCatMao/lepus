package com.catty.lepus.controller;

import com.catty.lepus.common.TokenDb;
import com.catty.lepus.constants.UserConstants;
import com.catty.lepus.constants.enums.StatusCode;
import com.catty.lepus.dto.ResultDto;
import com.catty.lepus.dto.TokenDto;
import com.catty.lepus.dto.dir.DirCreateDto;
import com.catty.lepus.exception.ResultException;
import com.catty.lepus.service.DirService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @Description 文件夹
 * @Author catty
 * @Date 2022/11/22 10:38
 **/
@Api(tags = "文件夹")
@CrossOrigin
@RestController
@RequestMapping("/api/dir")
@Slf4j
public class DirController {
    @Resource
    DirService dirService;

    @Autowired
    private TokenDb tokenDb;

    /**
     * 选中父节点，增加其下的文件夹
     * 创建同级 - 选中DirNode中的parentId
     * 创建子级 - 选在DirNode中的id
     *
     * @param request 请求体
     * @return 响应体
     */
    @ApiOperation(value = "选中父节点，增加其下的文件夹", notes = "文件夹")
    @PostMapping(value = "/add")
    public ResultDto addDir(HttpServletRequest request, @RequestBody DirCreateDto dirDto) {
        dirDto.validate();
        TokenDto tokenDto = tokenDb.getTokenDto(request.getHeader(UserConstants.LOGIN_TOKEN));
        log.info("tokenDb==" + tokenDb+";"+"tokenDb.getTokenDto=="+tokenDb.getTokenDto(request.getHeader(UserConstants.LOGIN_TOKEN)));
        Integer userId = tokenDto.getUserId();
        log.info("userId:" + userId);
        try {
            return ResultDto.success("文件夹添加成功", dirService.addDir(dirDto, userId));
        } catch (ResultException e) {
            throw new ResultException(e.getLocalizedMessage(), StatusCode.SERVICE_RUN_SUCCESS);
        } catch (Exception e) {
            log.error("[Dir add]Add dir failed. params={} e={} ", dirDto.toString(), e.getMessage());
            e.printStackTrace();
            return ResultDto.fail("添加文件夹失败", dirDto);
        }
    }


    /**
     * 新增、更新卡片下的目录树
     * 这里是返回没有头节点(顶级文件夹)的树
     *
     * @param productLineId 业务线id
     * @param channel       渠道
     * @return 响应体
     */
    @ApiOperation(value = "新增、更新卡片下的目录树", notes = "文件夹")
    @GetMapping(value = "/cardTree")
    public ResultDto getDirCardTree(HttpServletRequest servletRequest, @RequestParam @NotNull(message = "业务线id为空") Long productLineId,
                                    @RequestParam @NotNull(message = "渠道为空") Integer channel) {
        TokenDto tokenDto = tokenDb.getTokenDto(servletRequest.getHeader(UserConstants.LOGIN_TOKEN));

        try {
            return ResultDto.success("根目录获取成功", dirService.getDirTree(productLineId, channel, tokenDto.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.fail("根目录获取失败", e.getMessage());
        }
    }
}
